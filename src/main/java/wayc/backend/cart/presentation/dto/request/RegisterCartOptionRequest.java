package wayc.backend.cart.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.application.dto.request.RegisterOptionRequestDto;

@NoArgsConstructor
@Getter
public class RegisterCartOptionRequest {

    private String name;

    private Integer price;

    public RegisterCartOptionRequest(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public RegisterOptionRequestDto toServiceDto(){
        return RegisterOptionRequestDto
                .builder()
                .name(name)
                .price(price)
                .build();
    }
}
