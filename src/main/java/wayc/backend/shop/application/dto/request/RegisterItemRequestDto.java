package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.shop.presentation.dto.request.RegisterOptionGroupRequest;

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
    private Integer price;

    public RegisterItemRequestDto(List<RegisterOptionGroupRequest> dtos,
                                  String itemName,
                                  String imageUrl,
                                  String information,
                                  String category,
                                  Integer price) {
        this.itemName = itemName;
        this.imageUrl = imageUrl;
        this.information = information;
        this.category = category;
        this.price = price;
        this.optionGroups = dtos
                .stream()
                .map(dto -> new RegisterOptionGroupRequestDto(dto.getOptions(), dto.getOptionGroupName(), dto.getBasic()))
                .collect(Collectors.toList());
    }
}
