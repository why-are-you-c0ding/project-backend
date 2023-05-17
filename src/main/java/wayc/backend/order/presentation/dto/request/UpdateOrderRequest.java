package wayc.backend.order.presentation.dto.request;

import lombok.Getter;
import wayc.backend.order.application.dto.request.UpdateOrderRequestDto;
import wayc.backend.order.domain.OrderStatus;

@Getter
public class UpdateOrderRequest {

    private Long orderId;
    private Long itemId;
    private OrderStatus orderStatus;

    public UpdateOrderRequestDto toServiceDto(){
        return new UpdateOrderRequestDto(orderId, itemId, orderStatus);
    }

    public UpdateOrderRequest(Long orderId, Long itemId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderStatus = orderStatus;
    }

}
