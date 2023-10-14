package wayc.backend.security.exception;


import javax.security.auth.message.AuthException;

public class UnAuthorizedRedirectUrlException extends AuthException {

    private static final String message = "인증되지 않은 redirect url 입니다.";

    public UnAuthorizedRedirectUrlException() {
        super(message);
    }
}
