package wayc.backend.pay.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.pay.application.dto.request.CreatePayRequestDto;

@Getter
@NoArgsConstructor
public class PostPayRequestDto {
    private Integer price;
    private Long orderId;

    public PostPayRequestDto(Integer price, Long orderId) {
        this.price = price;
        this.orderId = orderId;
    }

    public CreatePayRequestDto toServiceDto(){
        return new CreatePayRequestDto(price, orderId);
    }
}
