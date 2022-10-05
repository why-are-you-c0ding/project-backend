package wayc.backend.shop.presentation.dto.response;

import lombok.Getter;

@Getter
public class PostStockResponseDto {

    private String message;

    public PostStockResponseDto() {
        this.message = "재고 등록에 성공했습니다";
    }
}


/**
 * TODO 프론트 요청에 따라 추후 수정
 */