package wayc.backend.cart.cart;

import org.springframework.http.HttpStatus;
import wayc.backend.verification.exception.VerificationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsCartException extends VerificationException {

    private static final String message = "존재하지 않는 카트입니다.";
    private static final String errorCode = "C001";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsCartException() {
        super(message, httpStatus, errorCode);
    }
}
