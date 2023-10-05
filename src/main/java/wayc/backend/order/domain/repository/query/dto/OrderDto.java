package wayc.backend.order.domain.repository.query.dto;

import wayc.backend.order.domain.OrderLineItemStatus;

public interface OrderDto {
    String getItemImageUrl();
    Long getOrderId();
    Integer getCount();
    String getItemName();
    String getCreatedAt();
    Long getItemId();
    OrderLineItemStatus getOrderStatus();
    Integer getPrice();
}