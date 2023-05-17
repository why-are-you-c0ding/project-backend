package wayc.backend.order.presentation.dto.response;

import lombok.Getter;

@Getter
public class CreateOrderResponse {

    private String message;

    public CreateOrderResponse() {
        this.message = "주문 생성에 성공하셨습니다.";
    }
}

