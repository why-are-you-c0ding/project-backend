package wayc.backend.order.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsPayException extends OrderException {

    private static final String message = "존재하지 않는 결제입니다.";
    private static final String errorCode = "P001";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsPayException() {
        super(message, httpStatus, errorCode);
    }
}
