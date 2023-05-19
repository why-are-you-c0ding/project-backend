package wayc.backend.cart.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.application.dto.request.RegisterOptionGroupRequestDto;
import wayc.backend.cart.application.dto.request.RegisterOptionRequestDto;

@NoArgsConstructor
@Getter
public class RegisterCartOptionGroupRequest {

    private RegisterCartOptionRequest cartOption;

    private String name;

    public RegisterCartOptionGroupRequest(RegisterCartOptionRequest cartOption, String name) {
        this.cartOption = cartOption;
        this.name = name;
    }

    public RegisterOptionGroupRequestDto toServiceDto(){
        return RegisterOptionGroupRequestDto
                .builder()
                .name(name)
                .cartOptions(
                        new RegisterOptionRequestDto(cartOption.getName(), cartOption.getPrice())
                )
                .build();
    }
}
