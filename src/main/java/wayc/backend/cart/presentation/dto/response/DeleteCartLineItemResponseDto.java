package wayc.backend.cart.presentation.dto.response;

import lombok.Getter;

@Getter
public class DeleteCartLineItemResponseDto{

    private String message;

    public DeleteCartLineItemResponseDto() {
        this.message = "장바구니 상품 삭제에 성공하셨습니다";
    }
}
