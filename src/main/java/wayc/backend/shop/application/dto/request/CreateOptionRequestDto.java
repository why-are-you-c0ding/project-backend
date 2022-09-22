package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateOptionRequestDto {

    private String optionName;
    private Integer price;

    public CreateOptionRequestDto(String optionName, Integer price) {
        this.optionName = optionName;
        this.price = price;
    }
}
