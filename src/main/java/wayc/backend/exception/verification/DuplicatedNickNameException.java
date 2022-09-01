package wayc.backend.exception.verification;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public class DuplicatedNickNameException extends VerificationException {

    private static final String message = "해당 닉네임은 이미 존재하는 닉네임입니다.";
    private static final String errorCode = "V002";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public DuplicatedNickNameException() {
        super(message, httpStatus, errorCode);
    }
}
