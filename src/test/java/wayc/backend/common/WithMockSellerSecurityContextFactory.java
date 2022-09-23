package wayc.backend.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import wayc.backend.security.token.JwtAuthenticationToken;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

import static java.util.Collections.singleton;

public class WithMockSellerSecurityContextFactory implements WithSecurityContextFactory<WithMockSeller> {

    @Override
    public SecurityContext createSecurityContext(WithMockSeller customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SELLER");
        JwtAuthenticationToken token = new JwtAuthenticationToken(
                customUser.principal(),
                customUser.password(),
                singleton(authority)
        );

        context.setAuthentication(token);
        return context;
    }
}
