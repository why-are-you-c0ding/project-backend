package wayc.backend.member.exception.email;

import org.springframework.http.HttpStatus;
import wayc.backend.member.exception.MemberException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsEmailException extends MemberException {

    private static final String message = "인증을 진행할 수 없는 이메일입니다..";
    private static final String errorCode = "E002";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsEmailException() {
        super(message, httpStatus, errorCode);
    }
}
