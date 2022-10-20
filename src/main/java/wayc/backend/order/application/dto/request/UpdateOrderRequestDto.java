package wayc.backend.order.application.dto.request;

import lombok.Getter;
import wayc.backend.order.domain.OrderStatus;

import javax.validation.constraints.NotNull;

@Getter
public class UpdateOrderRequestDto {

    @NotNull
    private Long orderId;

    @NotNull
    private Long itemId;

    @NotNull
    private OrderStatus orderStatus;

    public UpdateOrderRequestDto(Long orderId, Long itemId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderStatus = orderStatus;
    }
}
