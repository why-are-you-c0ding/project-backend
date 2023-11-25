package wayc.backend.payment.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.common.exception.ApplicationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class PaymentFailedException extends ApplicationException {

    private static final String message = "결제 요청을 실패했습니다.";
    private static final String errorCode = "p001";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public PaymentFailedException() {
        super(message, httpStatus, errorCode);
    }
}