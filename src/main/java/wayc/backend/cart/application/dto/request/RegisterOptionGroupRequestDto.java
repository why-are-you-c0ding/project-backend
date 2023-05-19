package wayc.backend.cart.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class RegisterOptionGroupRequestDto {

    private RegisterOptionRequestDto cartOptions;

    private String name;

    @Builder
    public RegisterOptionGroupRequestDto(RegisterOptionRequestDto cartOptions, String name) {
        this.cartOptions = cartOptions;
        this.name = name;
    }
}
