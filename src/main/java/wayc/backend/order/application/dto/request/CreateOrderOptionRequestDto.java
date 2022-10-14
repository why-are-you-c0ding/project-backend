package wayc.backend.order.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateOrderOptionRequestDto {

    private String name;
    private Integer price;

    public CreateOrderOptionRequestDto(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
