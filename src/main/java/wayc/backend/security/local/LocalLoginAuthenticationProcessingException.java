package wayc.backend.security.local;


import org.springframework.security.core.AuthenticationException;

public class LocalLoginAuthenticationProcessingException extends AuthenticationException {
    public LocalLoginAuthenticationProcessingException(String msg) {
        super(msg);
    }
}