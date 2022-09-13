package wayc.backend.security;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wayc.backend.security.handler.*;
import wayc.backend.security.provider.CustomAuthenticationProvider;
import wayc.backend.security.service.CustomUserDetailService;
import wayc.backend.verification.business.VerificationService;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final VerificationService verificationService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers("/docs/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors().configurationSource(corsConfigurationSource());

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**", "/verification/**")
                .permitAll()
                        .mvcMatchers("/**").permitAll();

        http.authenticationProvider(authenticationProvider());

        http.exceptionHandling()
                .authenticationEntryPoint(ajaxLoginAuthenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());


        /**
         * 리멤버 미 및 로그 아웃
         */
        http.rememberMe()
                .rememberMeParameter("remember-me")
                .alwaysRemember(true);

        http.logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID", "remember-me")
                .addLogoutHandler(logoutHandler())
                .logoutSuccessHandler(logoutSuccessHandler());

        customConfigurer(http);

        return http.build();
    }

    @Bean AuthenticationProvider authenticationProvider() throws NoSuchAlgorithmException {
        return new CustomAuthenticationProvider(userDetailsService(verificationService), passwordEncoder());
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
    public AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler(){
        return new AjaxAuthenticationFailureHandler();
    }

    @Bean
    public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler(){
        return new AjaxAuthenticationSuccessHandler();
    }

    @Bean
    public AjaxLoginAuthenticationEntryPoint ajaxLoginAuthenticationEntryPoint(){
        return new AjaxLoginAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
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
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID");
        serializer.setCookiePath("/");
        serializer.setDomainName("localhost:8080");
        //serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return serializer;
    }

    private void customConfigurer(HttpSecurity http) throws Exception {
        http
                .apply(new AjaxLoginConfigurer<>())
                .successHandlerAjax(ajaxAuthenticationSuccessHandler())
                .failureHandlerAjax(ajaxAuthenticationFailureHandler())
                .loginProcessingUrl("/login")
                .setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
    }
}
