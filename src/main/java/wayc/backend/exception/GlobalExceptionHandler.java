package wayc.backend.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.util.Objects.requireNonNull;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorCode = requireNonNull(e.getFieldError()).getDefaultMessage();
        ApiErrorResponse exceptionResponse = new ApiErrorResponse(errorCode);
        log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorCode, "@Valid");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionResponse);
    }

}


// Error : 예상하지 못한 심각한 문제가 발생하는 경우, 즉시 조취를 취해야 할 수준의 레벨
// Warn : 로직 상 유효성 확인, 예상 가능한 문제로 인한 예외 처리, 당장 서비스 운영에는 영향이 없지만 주의해야 할 부분
// Info : 운영에 참고할만한 사항, 중요한 비즈니스 프로세스가 완료됨
// Debug : 개발 단계에서 사용하며, SQL 로깅을 할 수 있음
// Trace : 모든 레벨에 대한 로깅이 추적되므로 개발 단계에서 사용함