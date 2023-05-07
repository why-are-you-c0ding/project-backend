package wayc.backend.shop.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.common.exception.ApplicationException;

public abstract class ShopException extends ApplicationException {
    public ShopException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}
