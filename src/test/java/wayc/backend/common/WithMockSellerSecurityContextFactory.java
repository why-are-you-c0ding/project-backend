package wayc.backend.common;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import wayc.backend.security.UserPrincipal;

import static java.util.Collections.singleton;

public class WithMockSellerSecurityContextFactory implements WithSecurityContextFactory<WithMockSeller> {

    @Override
    public SecurityContext createSecurityContext(WithMockSeller customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SELLER");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserPrincipal(customUser.principal(), "email", "pwd", singleton(authority) ),
                null,
                singleton(authority)
        );
        context.setAuthentication(token);
        return context;
    }
}
