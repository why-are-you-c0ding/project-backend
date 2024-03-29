package wayc.backend.shop.application.dto.response.find;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FindOptionGroupResponseDto {

    private Long optionGroupId;
    private String optionGroupName;
    private List<FindOptionResponseDto> options;

    public FindOptionGroupResponseDto(Long optionGroupId,
                                      String optionGroupName,
                                      List<FindOptionResponseDto> options) {
        this.optionGroupId = optionGroupId;
        this.optionGroupName = optionGroupName;
        this.options = options;

    }
}


