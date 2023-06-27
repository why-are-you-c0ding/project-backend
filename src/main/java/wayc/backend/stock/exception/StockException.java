package wayc.backend.stock.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.common.exception.ApplicationException;


public abstract class StockException extends ApplicationException {

    public StockException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}
