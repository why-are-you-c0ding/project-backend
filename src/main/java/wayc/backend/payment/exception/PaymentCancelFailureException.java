package wayc.backend.payment.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.common.exception.ApplicationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class PaymentCancelFailureException extends ApplicationException {

    private static final String message = "결제 취소를 실패 했습니다.";
    private static final String errorCode = "p002";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public PaymentCancelFailureException() {
        super(message, httpStatus, errorCode);
    }
}
