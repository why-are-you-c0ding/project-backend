package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.shop.presentation.dto.request.PostOptionGroupRequestDto;
//의존성이 반대로 흐르는데 일단 DTO <-> DTO 므로 이렇게하자
//대신에 DTO -> Entity 일 때는 컨버터 또는 매퍼를 두자.

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class CreateItemRequestDto {

    private String itemName;
    private String imageUrl;
    private String information;
    private List<CreateOptionGroupRequestDto> optionGroups;

    public CreateItemRequestDto(List<PostOptionGroupRequestDto> dtos, String itemName, String imageUrl, String information) {
        this.itemName = itemName;
        this.imageUrl = imageUrl;
        this.information = information;
        this.optionGroups = dtos
                .stream()
                .map(dto -> new CreateOptionGroupRequestDto(dto.getOptionRequests(), dto.getOptionGroupName(), dto.getBasic()))
                .collect(Collectors.toList());
    }
}
