package wayc.backend.member.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.member.exception.MemberException;

import static org.springframework.http.HttpStatus.*;

public class DuplicatedNickNameException extends MemberException {

    private static final String message = "해당 닉네임은 이미 존재하는 닉네임입니다.";
    private static final String errorCode = "M002";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public DuplicatedNickNameException() {
        super(message, httpStatus, errorCode);
    }
}
