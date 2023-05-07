package wayc.backend.verification.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.common.exception.ApplicationException;

public abstract class VerificationException extends ApplicationException {
    public VerificationException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}
