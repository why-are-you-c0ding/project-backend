package wayc.backend.order.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.application.dto.request.CreateOrderOptionRequestDto;

@Getter
@NoArgsConstructor
public class CreateOrderOptionRequest {

    private String name;
    private Integer price;

    public CreateOrderOptionRequest(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public CreateOrderOptionRequestDto toServiceDto(){
        return new CreateOrderOptionRequestDto(name, price);
    }
}
