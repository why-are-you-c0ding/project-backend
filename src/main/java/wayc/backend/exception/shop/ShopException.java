package wayc.backend.exception.shop;

import org.springframework.http.HttpStatus;
import wayc.backend.exception.ApplicationException;

public abstract class ShopException extends ApplicationException {
    public ShopException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}
