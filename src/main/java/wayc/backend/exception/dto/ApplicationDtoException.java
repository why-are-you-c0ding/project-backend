package wayc.backend.exception.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationDtoException {

    private String field;
    private String errorCode;
    private HttpStatus httpStatus;

    public ApplicationDtoException(String errorCode, String field, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.field = field;
        this.httpStatus = httpStatus;
    }
}
