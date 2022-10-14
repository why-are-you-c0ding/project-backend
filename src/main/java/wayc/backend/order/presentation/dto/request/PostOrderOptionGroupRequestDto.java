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
    @NotEmpty
    private List<PostOrderOptionRequestDto> orderOptions = new ArrayList<>();

    public PostOrderOptionGroupRequestDto(String name, List<PostOrderOptionRequestDto> orderOptionsDto) {
        this.name = name;
        this.orderOptions = orderOptionsDto;
    }

    public CreateOrderOptionGroupRequestDto toServiceDto(){
        return new CreateOrderOptionGroupRequestDto(
                name,
                orderOptions
                        .stream()
                        .map(optionRequestDto -> optionRequestDto.toServiceDto())
                        .collect(Collectors.toList())
        );
    }
}
