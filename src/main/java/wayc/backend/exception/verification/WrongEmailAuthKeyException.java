package wayc.backend.exception.verification;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class WrongEmailAuthKeyException extends VerificationException {

    private static final String message = "이메일 인증번호가 틀립니다.";
    private static final String errorCode = "V008";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public WrongEmailAuthKeyException() {
        super(message, httpStatus, errorCode);
    }
}
