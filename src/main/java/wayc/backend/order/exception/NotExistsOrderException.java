package wayc.backend.order.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.member.exception.MemberException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsOrderException extends MemberException {

    private static final String message = "존재하지 않는 주문입니다.";
    private static final String errorCode = "O001";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsOrderException() {
        super(message, httpStatus, errorCode);
    }
}
