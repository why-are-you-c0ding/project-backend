package wayc.backend.member.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.member.exception.MemberException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class FailSendEmailException extends MemberException {

    private static final String message = "이메일 전송에 실패했습니다.";
    private static final String errorCode = "V004";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public FailSendEmailException() {
        super(message, httpStatus, errorCode);
    }
}
