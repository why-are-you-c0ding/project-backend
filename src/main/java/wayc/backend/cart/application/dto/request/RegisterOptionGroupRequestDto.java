package wayc.backend.cart.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class RegisterOptionGroupRequestDto {

    private List<RegisterOptionRequestDto> cartOptions = new ArrayList<>();

    private String name;

    @Builder
    public RegisterOptionGroupRequestDto(List<RegisterOptionRequestDto> cartOptions, String name) {
        this.cartOptions = cartOptions;
        this.name = name;
    }
}
