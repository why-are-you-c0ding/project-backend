package wayc.backend.order.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.application.dto.request.CreateOrderOptionRequestDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostOrderOptionRequestDto {

    private String name;
    private Integer price;

    public PostOrderOptionRequestDto(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public CreateOrderOptionRequestDto toServiceDto(){
        return new CreateOrderOptionRequestDto(
                name,
                price
        );
    }
}
