package wayc.backend.member.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.member.exception.MemberException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsMemberException extends MemberException {

    private static final String message = "존재하지 않는 유저입니다.";
    private static final String errorCode = "M003";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsMemberException() {
        super(message, httpStatus, errorCode);
    }
}
