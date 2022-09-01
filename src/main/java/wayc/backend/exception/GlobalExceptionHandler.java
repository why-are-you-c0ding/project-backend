package wayc.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wayc.backend.exception.dto.ApplicationExceptionResponse;

import static org.springframework.http.HttpStatus.*;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String DATABASE_SERVER_ERROR_CODE = "데이터베이스 서버 오류입니다.";
    private static final String INTERNAL_SERVER_ERROR_CODE = "서버 오류입니다.";

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationExceptionResponse> applicationException(ApplicationException e) {
        //log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorCode, "@Valid");
        ApplicationExceptionResponse exceptionResponse = new ApplicationExceptionResponse(e.getMessage(), e.getErrorCode(), e.getHttpStatus());
        return ResponseEntity.status(e.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getFieldError().getDefaultMessage();
        ApplicationExceptionResponse exceptionResponse = new ApplicationExceptionResponse(message, "D001", BAD_REQUEST);
        //log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorCode, "@Valid");
        return ResponseEntity.status(BAD_REQUEST.value()).body(exceptionResponse);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApplicationExceptionResponse> dataAccessException(DataAccessException e) {
        ApplicationExceptionResponse exceptionResponse = new ApplicationExceptionResponse(DATABASE_SERVER_ERROR_CODE, "S003", BAD_REQUEST);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApplicationExceptionResponse> runtimeException(RuntimeException e) {
        ApplicationExceptionResponse exceptionResponse = new ApplicationExceptionResponse(INTERNAL_SERVER_ERROR_CODE, "S002", INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApplicationExceptionResponse> runtimeException(Exception e) {
        ApplicationExceptionResponse exceptionResponse = new ApplicationExceptionResponse(INTERNAL_SERVER_ERROR_CODE, "S001", INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }
}

// Error
// Warn
// Info
// Debug
// Trace