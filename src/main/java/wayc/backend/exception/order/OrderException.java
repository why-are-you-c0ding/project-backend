package wayc.backend.exception.order;

import org.springframework.http.HttpStatus;
import wayc.backend.exception.ApplicationException;

public abstract class OrderException extends ApplicationException {
    public OrderException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}
