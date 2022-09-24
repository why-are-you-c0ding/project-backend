package wayc.backend.shop.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CreateStockResponseDto {

    private String message;

    public CreateStockResponseDto() {
        this.message = "재고 등록에 성공했습니다";
    }
}


/**
 * TODO 프론트 요청에 따라 추후 수정
 */