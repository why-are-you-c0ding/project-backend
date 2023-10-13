package wayc.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.security.*;
import wayc.backend.security.handler.CustomAccessDeniedHandler;
import wayc.backend.security.handler.CustomAuthenticationEntryPoint;
import wayc.backend.security.handler.CustomLoginSuccessHandler;
import wayc.backend.security.handler.CustomLogoutSuccessHandler;
import wayc.backend.security.local.LocalLoginFilter;
import wayc.backend.security.oauth2.CustomOAuth2UserService;
import wayc.backend.security.oauth2.OAuth2AuthenticationFailureHandler;
import wayc.backend.security.oauth2.OAuth2AuthenticationSuccessHandler;

import javax.servlet.Filter;
import java.util.UUID;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final AppProperties appProperties;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /**
         * HttpSecurity에서 alwaysRememberMe가 안먹어서 직접 설정.
         */
        String rememberMeServiceKey = UUID.randomUUID().toString();
        RememberMeServices rememberMeServices = rememberMeServices(rememberMeServiceKey);
        AbstractRememberMeServices abstractRememberMeServices = (AbstractRememberMeServices) rememberMeServices;
        abstractRememberMeServices.setAlwaysRemember(true);

        http
                .cors()
                .and()
                .rememberMe()
                .rememberMeServices(rememberMeServices)
                .key(rememberMeServiceKey)
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .and()
                .sessionFixation().changeSessionId()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))
                .accessDeniedHandler(new CustomAccessDeniedHandler(objectMapper))
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/auth/**", "/oauth2/**", "/actuator/**", "/health-check/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/local/login/**")
                .permitAll()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                //.authorizationRequestRepository()
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService())
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler())
                .failureHandler(oAuth2AuthenticationFailureHandler());


        http.addFilterBefore(cookieSessionAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(loginFilter(rememberMeServices), UsernamePasswordAuthenticationFilter.class);

        http.logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID", "remember-me")
//                .addLogoutHandler(logoutHandler()) 디버깅해보니 4개의 핸들러가 이미 있다.
                .logoutSuccessHandler(logoutSuccessHandler());

        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        return http.build();
    }

    private CookieSessionAuthenticationFilter cookieSessionAuthenticationFilter() {
        return new CookieSessionAuthenticationFilter(customUserDetailsService());
    }


    private RememberMeServices rememberMeServices(String key) {
        return new PersistentTokenBasedRememberMeServices(key, customUserDetailsService(), new InMemoryTokenRepositoryImpl());
    }

    private Filter loginFilter(RememberMeServices rememberMeServices) {
        LocalLoginFilter loginFilter = new LocalLoginFilter(objectMapper);
        loginFilter.setFilterProcessesUrl("/local/login");
        loginFilter.setAuthenticationManager(authenticationManager());
        loginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        loginFilter.setRememberMeServices(rememberMeServices);
        return loginFilter;
    }


    Filter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Bean
    AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(customUserDetailsService());
        return authenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService(memberRepository);
    }

    @Bean
    CustomLogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler(objectMapper);
    }

    @Bean
    AuthenticationSuccessHandler loginSuccessHandler() {
        return new CustomLoginSuccessHandler(objectMapper);
    }

    @Bean
    CustomOAuth2UserService customOAuth2UserService(){
        return new CustomOAuth2UserService(memberRepository);
    }

    @Bean
    OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler(){
        return new OAuth2AuthenticationFailureHandler(appProperties);
    }

    @Bean
    OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler(){
        return new OAuth2AuthenticationSuccessHandler(appProperties);
    }
}
