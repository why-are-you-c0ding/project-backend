package wayc.backend.exception.cart;

import org.springframework.http.HttpStatus;
import wayc.backend.exception.verification.VerificationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsCartLineException extends VerificationException {

    private static final String message = "존재하지 않는 카트 상품입니다.";
    private static final String errorCode = "C002";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsCartLineException() {
        super(message, httpStatus, errorCode);
    }
}
