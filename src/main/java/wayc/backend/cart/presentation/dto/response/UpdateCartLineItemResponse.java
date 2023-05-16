package wayc.backend.cart.presentation.dto.response;

import lombok.Getter;

@Getter
public class UpdateCartLineItemResponse {

    private String message;
    public UpdateCartLineItemResponse() {
        this.message = "장바구니 상품 업데이트를 성공하셨습니다";
    }
}
