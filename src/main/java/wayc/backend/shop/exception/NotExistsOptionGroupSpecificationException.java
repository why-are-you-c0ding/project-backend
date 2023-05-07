package wayc.backend.shop.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsOptionGroupSpecificationException extends ShopException{

    private static final String message = "존재하지 않는 OptionGroup 입니다.";
    private static final String errorCode = "i002";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsOptionGroupSpecificationException() {
        super(message, httpStatus, errorCode);
    }
}



