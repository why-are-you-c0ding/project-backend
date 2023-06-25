package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.application.dto.request.RegisterOptionRequestDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
public class RegisterOptionRequest {

    @Size(min = 4)
    private String optionName;

    @Min(1000)
    private Integer price;

    public RegisterOptionRequest(String optionName, Integer price) {
        this.optionName = optionName;
        this.price = price;
    }

    public RegisterOptionRequestDto toServiceDto(){
        return new RegisterOptionRequestDto(optionName, price);
    }
}
