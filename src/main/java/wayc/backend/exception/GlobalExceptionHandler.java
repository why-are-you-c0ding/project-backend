package wayc.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wayc.backend.exception.dto.ApplicationDtoException;

import javax.persistence.ElementCollection;

import static org.springframework.http.HttpStatus.*;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationException> applicationException(ApplicationException e) {
        //log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorCode, "@Valid");
        return ResponseEntity.status(e.getHttpStatus()).body(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationDtoException> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorCode = e.getFieldError().getDefaultMessage();
        String target = e.getFieldError().getField();
        ApplicationDtoException exceptionResponse = new ApplicationDtoException(errorCode, target, BAD_REQUEST);
        //log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorCode, "@Valid");
        return ResponseEntity.status(BAD_REQUEST.value()).body(exceptionResponse);
    }
}

// Error
// Warn
// Info
// Debug
// Trace