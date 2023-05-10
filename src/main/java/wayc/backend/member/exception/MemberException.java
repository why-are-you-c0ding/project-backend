package wayc.backend.member.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.common.exception.ApplicationException;

public abstract class MemberException extends ApplicationException {
    public MemberException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}
