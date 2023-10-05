package wayc.backend.order.application.dto.request;

import lombok.Getter;
import wayc.backend.order.domain.OrderLineItemStatus;

import javax.validation.constraints.NotNull;

@Getter
public class UpdateOrderRequestDto {

    @NotNull
    private Long orderId;

    @NotNull
    private Long itemId;

    @NotNull
    private OrderLineItemStatus orderStatus;

    public UpdateOrderRequestDto(Long orderId, Long itemId, OrderLineItemStatus orderStatus) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderStatus = orderStatus;
    }
}
