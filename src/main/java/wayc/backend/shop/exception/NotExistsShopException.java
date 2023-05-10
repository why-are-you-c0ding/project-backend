package wayc.backend.shop.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NotExistsShopException extends ShopException{

    private static final String message = "존재하지 않는 shop 입니다.";
    private static final String errorCode = "S001";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public NotExistsShopException() {
        super(message, httpStatus, errorCode);
    }
}



