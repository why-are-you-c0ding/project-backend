package wayc.backend.security;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import wayc.backend.security.handler.AjaxAuthenticationFailureHandler;
import wayc.backend.security.handler.AjaxAuthenticationSuccessHandler;
import wayc.backend.security.handler.AjaxLoginAuthenticationEntryPoint;
import wayc.backend.security.handler.CustomAccessDeniedHandler;
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
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**", "/verification/**")
                .permitAll()
                        .mvcMatchers("/**").permitAll();

        http.authenticationProvider(authenticationProvider());

        http.exceptionHandling()
                .authenticationEntryPoint(ajaxLoginAuthenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());

        http.rememberMe()
                .rememberMeParameter("remember-me")
                .alwaysRemember(true);

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

    private void customConfigurer(HttpSecurity http) throws Exception {
        http
                .apply(new AjaxLoginConfigurer<>())
                .successHandlerAjax(ajaxAuthenticationSuccessHandler())
                .failureHandlerAjax(ajaxAuthenticationFailureHandler())
                .loginProcessingUrl("/login")
                .setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
    }
}
