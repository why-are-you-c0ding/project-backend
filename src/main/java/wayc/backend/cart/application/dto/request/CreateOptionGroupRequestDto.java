package wayc.backend.cart.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class CreateOptionGroupRequestDto {

    private List<CreateOptionRequestDto> cartOptions = new ArrayList<>();

    private String name;

    @Builder
    public CreateOptionGroupRequestDto(List<CreateOptionRequestDto> cartOptions, String name) {
        this.cartOptions = cartOptions;
        this.name = name;
    }
}
