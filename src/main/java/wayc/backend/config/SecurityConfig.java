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
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.security.*;
import wayc.backend.security.local.LocalLoginFilter;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import java.util.UUID;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        String rememberMeServiceKey = UUID.randomUUID().toString();
        RememberMeServices rememberMeServices = rememberMeServices(rememberMeServiceKey);

        http
                .cors()
                .and()
                .rememberMe()
                .alwaysRemember(true)
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
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
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
                .permitAll();



        http.addFilterBefore(loginFilter(rememberMeServices), UsernamePasswordAuthenticationFilter.class);

        http.logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID", "remember-me")
//                .addLogoutHandler(logoutHandler()) 디버깅해보니 4개의 핸들러가 이미 있다.
                .logoutSuccessHandler(logoutSuccessHandler());

        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        return http.build();
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
}
