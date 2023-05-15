package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RegisterOptionRequestDto {

    private String optionName;
    private Integer price;

    public RegisterOptionRequestDto(String optionName, Integer price) {
        this.optionName = optionName;
        this.price = price;
    }
}
