package wayc.backend.pay.application.dto.request;

import lombok.Getter;

@Getter
public class CreatePayRequestDto {

    private Integer price;
    private Long orderId;

    public CreatePayRequestDto(Integer price, Long orderId) {
        this.price = price;
        this.orderId = orderId;
    }
}
