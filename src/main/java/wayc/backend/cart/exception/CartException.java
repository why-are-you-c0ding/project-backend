package wayc.backend.cart.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.common.exception.ApplicationException;

public abstract class CartException extends ApplicationException {
    public CartException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}
