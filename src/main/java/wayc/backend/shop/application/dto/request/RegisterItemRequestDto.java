package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.shop.presentation.dto.request.RegisterOptionGroupRequest;
//의존성이 반대로 흐르는데 일단 DTO <-> DTO 므로 이렇게하자
//대신에 DTO -> Entity 일 때는 컨버터 또는 매퍼를 두자.

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class RegisterItemRequestDto {

    private String itemName;
    private String imageUrl;
    private String information;
    private List<RegisterOptionGroupRequestDto> optionGroups;
    private String category;

    public RegisterItemRequestDto(List<RegisterOptionGroupRequest> dtos, String itemName, String imageUrl, String information, String category) {
        this.itemName = itemName;
        this.imageUrl = imageUrl;
        this.information = information;
        this.category = category;
        this.optionGroups = dtos
                .stream()
                .map(dto -> new RegisterOptionGroupRequestDto(dto.getOptions(), dto.getOptionGroupName(), dto.getBasic()))
                .collect(Collectors.toList());
    }
}
