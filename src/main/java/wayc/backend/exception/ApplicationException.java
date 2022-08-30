package wayc.backend.exception;

import org.springframework.http.HttpStatus;

public abstract class ApplicationException extends RuntimeException{

    private HttpStatus httpStatus;
    private String errorCode;

    public ApplicationException(String message, HttpStatus httpStatus, String errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
