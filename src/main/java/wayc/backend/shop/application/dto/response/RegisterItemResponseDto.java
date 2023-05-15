package wayc.backend.shop.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RegisterItemResponseDto {

    private Long itemId;

    public RegisterItemResponseDto(Long itemId) {
        this.itemId = itemId;
    }
}
