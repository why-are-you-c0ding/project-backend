package wayc.backend.security;

import com.nimbusds.jose.jwk.KeyType;
import jdk.jshell.spi.SPIResolutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@RequiredArgsConstructor
public class CookieSessionAuthenticationFilter extends OncePerRequestFilter {

    private static final String KEY = "SPRING_SECURITY_CONTEXT";

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(false);
            if(session != null) {
                Object principalObj = session.getAttribute(KEY);
                if(principalObj != null & principalObj.getClass() == UserPrincipal.class) {
                    UserPrincipal principal = (UserPrincipal) principalObj;
                    UserDetails userDetails = customUserDetailsService.loadUserById(principal.getId());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
    }
}
