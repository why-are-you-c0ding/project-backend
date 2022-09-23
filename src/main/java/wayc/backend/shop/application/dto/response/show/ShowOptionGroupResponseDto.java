package wayc.backend.shop.application.dto.response.show;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.domain.Item;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ShowOptionGroupResponseDto {

    private Long optionGroupId;
    private String optionGroupName;
    private List<ShowOptionResponseDto> optionGroupSpecificationIdList;

    public ShowOptionGroupResponseDto(Long optionGroupId, String optionGroupName, List<ShowOptionResponseDto> optionGroupSpecificationIdList) {
        this.optionGroupId = optionGroupId;
        this.optionGroupName = optionGroupName;
        this.optionGroupSpecificationIdList = optionGroupSpecificationIdList;
    }
}


