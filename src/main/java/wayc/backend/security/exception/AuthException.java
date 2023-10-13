package wayc.backend.security.exception;

import lombok.Getter;

@Getter
public abstract class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }
}
