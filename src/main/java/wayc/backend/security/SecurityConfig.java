package wayc.backend.security;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import wayc.backend.security.handler.*;
import wayc.backend.security.provider.TokenAuthenticationProvider;
import wayc.backend.security.service.CustomUserDetailService;
import wayc.backend.security.service.JwtProvider;
import wayc.backend.verification.application.VerificationService;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final VerificationService verificationService;
    private final JwtProvider jwtProvider;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers("/docs/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors().configurationSource(corsConfigurationSource());


        //토큰을 사용하므로 csrf를 disable 한다.
        http.csrf().disable();

        http.authenticationProvider(authenticationProvider());

        http.exceptionHandling()
                .authenticationEntryPoint(ajaxLoginAuthenticationEntryPoint()) //TODO JWT 관련으로 수정해야 함.
                .accessDeniedHandler(accessDeniedHandler()); //TODO JWT 관련으로 수정해야 함.


        /**
         * 리멤버 미 및 로그 아웃
         */

        http.logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID", "remember-me")
                .addLogoutHandler(logoutHandler())
                .logoutSuccessHandler(logoutSuccessHandler());

        /**
         * 세션 고정 보호
         */
        http.sessionManagement()
                .sessionFixation()
                .changeSessionId();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션 사용 안함

        customConfigurer(http);

        return http.build();
    }

    @Bean AuthenticationProvider authenticationProvider() throws NoSuchAlgorithmException {
        return new TokenAuthenticationProvider(userDetailsService(verificationService), passwordEncoder());
    }

    @Bean UserDetailsService userDetailsService(VerificationService verificationService){
        return new CustomUserDetailService(verificationService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        return new BCryptPasswordEncoder(10, random);//강도 계수를 지정하고 인코딩에 이용되는 SecureRandom 인스턴스를 변경 가능.
    }

    @Bean
    public TokenAuthenticationFailureHandler ajaxAuthenticationFailureHandler(){
        return new TokenAuthenticationFailureHandler();
    }

    @Bean
    public TokenAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler(){
        return new TokenAuthenticationSuccessHandler(jwtProvider);
    }

    @Bean
    public TokenAuthenticationEntryPoint ajaxLoginAuthenticationEntryPoint(){
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

    private void customConfigurer(HttpSecurity http) throws Exception {
        http
                .apply(new TokenLoginConfigurer<>())
                .successHandlerAjax(ajaxAuthenticationSuccessHandler())
                .failureHandlerAjax(ajaxAuthenticationFailureHandler())
                .loginProcessingUrl("/login")
                .setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
    }

}
