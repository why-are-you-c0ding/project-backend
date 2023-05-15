package wayc.backend.shop.application.dto.response.find;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindOptionResponseDto {

    private Long optionId;
    private String optionName;
    private Integer price;

    public FindOptionResponseDto(Long optionId, String optionName, Integer price) {
        this.optionId = optionId;
        this.optionName = optionName;
        this.price = price;
    }
}


