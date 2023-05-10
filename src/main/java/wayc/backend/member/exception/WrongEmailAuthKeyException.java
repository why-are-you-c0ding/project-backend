package wayc.backend.member.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.member.exception.MemberException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class WrongEmailAuthKeyException extends MemberException {

    private static final String message = "이메일 인증번호가 틀립니다.";
    private static final String errorCode = "V008";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public WrongEmailAuthKeyException() {
        super(message, httpStatus, errorCode);
    }
}
