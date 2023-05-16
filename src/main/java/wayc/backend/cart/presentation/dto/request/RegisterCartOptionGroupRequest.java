package wayc.backend.cart.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.application.dto.request.RegisterOptionGroupRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class RegisterCartOptionGroupRequest {

    private List<RegisterCartOptionRequest> cartOptions = new ArrayList<>();

    private String name;

    public RegisterCartOptionGroupRequest(List<RegisterCartOptionRequest> cartOptions, String name) {
        this.cartOptions = cartOptions;
        this.name = name;
    }

    public RegisterOptionGroupRequestDto toServiceDto(){
        return RegisterOptionGroupRequestDto
                .builder()
                .name(name)
                .cartOptions(
                        cartOptions
                                .stream()
                                .map(RegisterCartOptionRequest::toServiceDto)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
