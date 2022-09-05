package wayc.backend.exception.verification;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsMemberException extends VerificationException {

    private static final String message = "존재하지 않는 유저입니다.";
    private static final String errorCode = "V005";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsMemberException() {
        super(message, httpStatus, errorCode);
    }
}
