package wayc.backend.security.token;
;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken { //UsernamePasswordAuthenticationToken과 매우 유사

    private Object principal;
    private Object credentials;
    private Long id;

    public JwtAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.id = null;
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Long id) {
        super(authorities);
        this.id = id;
        this.principal = principal;
        this.credentials = credentials; //이걸 굳이 넣어야 할까?
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public Long getId() {
        return id;
    }
}
