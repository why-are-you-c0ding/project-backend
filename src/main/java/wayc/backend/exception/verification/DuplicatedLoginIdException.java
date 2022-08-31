package wayc.backend.exception.verification;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class DuplicatedLoginIdException extends VerificationException {

    private static final String message = "해당 로그인 아이디는 이미 존재하는 로그인 아이디입니다.";
    private static final String errorCode = "V003";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public DuplicatedLoginIdException() {
        super(message, httpStatus, errorCode);
    }
}
