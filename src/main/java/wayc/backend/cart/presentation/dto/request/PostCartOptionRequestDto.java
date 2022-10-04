package wayc.backend.cart.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.application.dto.request.CreateOptionRequestDto;

@NoArgsConstructor
@Getter
public class PostCartOptionRequestDto {

    private String name;

    private Integer price;

    public PostCartOptionRequestDto(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public CreateOptionRequestDto toServiceDto(){
        return CreateOptionRequestDto
                .builder()
                .name(name)
                .price(price)
                .build();
    }
}
