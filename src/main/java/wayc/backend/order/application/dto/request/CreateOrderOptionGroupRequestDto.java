package wayc.backend.order.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateOrderOptionGroupRequestDto {

    private String name;

    private CreateOrderOptionRequestDto orderOptionsDto;

    public CreateOrderOptionGroupRequestDto(String name, CreateOrderOptionRequestDto orderOptionsDto) {
        this.name = name;
        this.orderOptionsDto = orderOptionsDto;
    }
}
