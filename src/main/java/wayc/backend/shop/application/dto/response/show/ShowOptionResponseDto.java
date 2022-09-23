package wayc.backend.shop.application.dto.response.show;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.domain.Item;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ShowOptionResponseDto {

    private Long optionId;
    private String optionName;
    private Integer price;


    public ShowOptionResponseDto(Long optionId, String optionName, Integer price) {
        this.optionId = optionId;
        this.optionName = optionName;
        this.price = price;
    }
}


