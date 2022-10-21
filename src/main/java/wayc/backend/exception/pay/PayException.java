package wayc.backend.exception.pay;

import org.springframework.http.HttpStatus;
import wayc.backend.exception.ApplicationException;

public abstract class PayException extends ApplicationException {
    public PayException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}
