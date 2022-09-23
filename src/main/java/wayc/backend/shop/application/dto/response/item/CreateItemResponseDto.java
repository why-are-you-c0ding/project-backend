package wayc.backend.shop.application.dto.response.item;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateItemResponseDto {

    private Long itemId;

    public CreateItemResponseDto(Long itemId) {
        this.itemId = itemId;
    }
}
