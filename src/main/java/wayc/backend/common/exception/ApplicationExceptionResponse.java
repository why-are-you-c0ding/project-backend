package wayc.backend.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationExceptionResponse {

    private String message;
    private String errorCode;
    private HttpStatus httpStatus;

    public ApplicationExceptionResponse(String message, String errorCode, HttpStatus httpStatus) {
        this.message = message;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
