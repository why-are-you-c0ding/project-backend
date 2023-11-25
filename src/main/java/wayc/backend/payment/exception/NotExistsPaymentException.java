package wayc.backend.payment.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.common.exception.ApplicationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsPaymentException extends ApplicationException {

    private static final String message = "존재하지 않는 결제입니다.";
    private static final String errorCode = "p002";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsPaymentException() {
        super(message, httpStatus, errorCode);
    }
}
