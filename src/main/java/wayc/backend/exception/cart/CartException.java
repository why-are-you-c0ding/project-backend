package wayc.backend.exception.cart;

import org.springframework.http.HttpStatus;
import wayc.backend.exception.ApplicationException;

public abstract class CartException extends ApplicationException {
    public CartException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}
