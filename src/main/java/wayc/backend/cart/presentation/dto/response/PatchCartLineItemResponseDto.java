package wayc.backend.cart.presentation.dto.response;

import lombok.Getter;

@Getter
public class PatchCartLineItemResponseDto {

    private String message;
    public PatchCartLineItemResponseDto() {
        this.message = "장바구니 상품 업데이트를 성공하셨습니다";
    }
}
