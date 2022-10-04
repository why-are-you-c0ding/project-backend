package wayc.backend.cart.presentation.dto.response;

import lombok.Getter;

@Getter
public class PostCartLineItemResponseDto {

    private String message;


    public PostCartLineItemResponseDto() {
        this.message = "장바구니에 상품 담는 것을 성공하셨습니다.";
    }
}
