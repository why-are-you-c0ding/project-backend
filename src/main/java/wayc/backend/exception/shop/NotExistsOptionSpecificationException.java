package wayc.backend.exception.shop;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsOptionSpecificationException extends ShopException{

    private static final String message = "존재하지 않는 Option 입니다.";
    private static final String errorCode = "i003";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsOptionSpecificationException() {
        super(message, httpStatus, errorCode);
    }
}



