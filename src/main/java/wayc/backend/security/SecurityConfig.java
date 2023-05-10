package wayc.backend.security;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.security.filter.TokenAuthorizationFilter;
import wayc.backend.security.handler.*;
import wayc.backend.security.provider.TokenAuthenticationProvider;
import wayc.backend.security.service.CustomUserDetailService;
import wayc.backend.security.jwt.JwtProvider;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers("/docs/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource());

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/items").hasRole("SELLER")
                .antMatchers(HttpMethod.GET,"/items/sellers").hasRole("SELLER")
                .antMatchers(HttpMethod.POST, "/stocks").hasRole("SELLER")
                .antMatchers("/carts/**", "/images/**").access("hasRole('SELLER') or hasRole('CONSUMER')");

        http
                .authenticationManager(authenticationManager());

        http
                .exceptionHandling()
                .authenticationEntryPoint(loginAuthenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());

        http
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler(logoutHandler())
                .logoutSuccessHandler(logoutSuccessHandler());

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션 사용 안함

        customConfigurer(http);

        return http.build();
    }

    @Bean AuthenticationProvider authenticationProvider() throws NoSuchAlgorithmException {
        return new TokenAuthenticationProvider(userDetailsService(), passwordEncoder());
    }

    @Bean UserDetailsService userDetailsService(){
        return new CustomUserDetailService(memberRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        return new BCryptPasswordEncoder(10, random);//강도 계수를 지정하고 인코딩에 이용되는 SecureRandom 인스턴스를 변경 가능.
    }

    @Bean
    public TokenAuthenticationFailureHandler authenticationFailureHandler(){
        return new TokenAuthenticationFailureHandler();
    }

    @Bean
    public TokenAuthenticationSuccessHandler authenticationSuccessHandler(){
        return new TokenAuthenticationSuccessHandler(jwtProvider);
    }

    @Bean
    public TokenAuthenticationEntryPoint loginAuthenticationEntryPoint(){
        return new TokenAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new TokenAccessDeniedHandler();
    }

    @Bean
    public LogoutHandler logoutHandler() {
            return new CustomLogoutHandler();
        }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3090");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    AuthenticationManager authenticationManager() throws NoSuchAlgorithmException {
        return new ProviderManager(authenticationProvider());
    }

    private void customConfigurer(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(
                        new TokenAuthorizationFilter(authenticationManager(), jwtProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .apply(new TokenLoginConfigurer<>())
                .successHandlerAjax(authenticationSuccessHandler())
                .failureHandlerAjax(authenticationFailureHandler())
                .loginProcessingUrl("/login")
                .setAuthenticationManager(authenticationManager());
    }
}
