package wayc.backend.pay.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.member.exception.MemberException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsPayException extends MemberException {

    private static final String message = "존재하지 않는 결제입니다.";
    private static final String errorCode = "P001";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsPayException() {
        super(message, httpStatus, errorCode);
    }
}