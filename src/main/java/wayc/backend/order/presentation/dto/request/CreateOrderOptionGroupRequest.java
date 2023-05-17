package wayc.backend.order.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.application.dto.request.CreateOrderOptionGroupRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateOrderOptionGroupRequest {

    @NotBlank
    private String name;

    @Valid
    private CreateOrderOptionRequest orderOption;

    public CreateOrderOptionGroupRequest(String name, CreateOrderOptionRequest orderOption) {
        this.name = name;
        this.orderOption = orderOption;
    }

    public CreateOrderOptionGroupRequestDto toServiceDto(){
        return new CreateOrderOptionGroupRequestDto(name, orderOption.toServiceDto());
    }
}
