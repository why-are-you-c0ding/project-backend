package wayc.backend.order.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderOptionGroupRequestDto {

    private String name;

    private List<CreateOrderOptionRequestDto> orderOptionsDto = new ArrayList<>();

    public CreateOrderOptionGroupRequestDto(String name, List<CreateOrderOptionRequestDto> orderOptionsDto) {
        this.name = name;
        this.orderOptionsDto = orderOptionsDto;
    }
}
