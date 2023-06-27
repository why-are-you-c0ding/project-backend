package wayc.backend.stock.exception;

import org.springframework.http.HttpStatus;
import wayc.backend.shop.exception.ShopException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class CantResolveStockException extends ShopException {

    private static final String message = "재고 조회 요청과 다릅니다.";
    private static final String errorCode = "s001";
    private static final HttpStatus httpStatus = BAD_REQUEST;

    public CantResolveStockException() {
        super(message, httpStatus, errorCode);
    }
}