package wayc.backend.exception.verification;

import org.springframework.http.HttpStatus;
import wayc.backend.exception.ApplicationException;

public abstract class VerificationException extends ApplicationException {
    public VerificationException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}
