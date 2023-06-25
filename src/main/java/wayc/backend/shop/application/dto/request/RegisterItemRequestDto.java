package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class RegisterItemRequestDto {

    private String itemName;
    private String imageUrl;
    private String information;
    private List<RegisterOptionGroupRequestDto> optionGroups;
    private String category;
    private Integer price;

    public RegisterItemRequestDto(List<RegisterOptionGroupRequestDto> dtos,
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
        this.optionGroups = dtos;
    }
}
