package wayc.backend.order.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.application.dto.request.CreateOrderOptionGroupRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderOptionRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostOrderOptionGroupRequestDto {

    @NotBlank
    private String name;

    @Valid
    private PostOrderOptionRequestDto orderOption;

    public PostOrderOptionGroupRequestDto(String name, PostOrderOptionRequestDto orderOption) {
        this.name = name;
        this.orderOption = orderOption;
    }

    public CreateOrderOptionGroupRequestDto toServiceDto(){
        return new CreateOrderOptionGroupRequestDto(name, orderOption.toServiceDto());
    }
}
