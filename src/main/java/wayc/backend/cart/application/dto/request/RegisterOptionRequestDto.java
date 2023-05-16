package wayc.backend.cart.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RegisterOptionRequestDto {

    private String name;

    private Integer price;

    @Builder
    public RegisterOptionRequestDto(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
