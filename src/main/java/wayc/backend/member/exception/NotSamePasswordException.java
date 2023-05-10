package wayc.backend.member.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.member.exception.MemberException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotSamePasswordException extends MemberException {

    private static final String message = "두 비밀번호가 일치하지 않습니다.";
    private static final String errorCode = "M004";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotSamePasswordException() {
        super(message, httpStatus, errorCode);
    }
}
