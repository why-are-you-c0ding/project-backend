package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
public class PostOptionRequestDto {

    @Size(min = 4)
    private String optionName;

    @Min(1000)
    private Integer price;

    public PostOptionRequestDto(String optionName, Integer price) {
        this.optionName = optionName;
        this.price = price;
    }
}
