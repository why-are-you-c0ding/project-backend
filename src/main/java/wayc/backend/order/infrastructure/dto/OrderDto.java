package wayc.backend.order.infrastructure.dto;

import wayc.backend.order.domain.OrderStatus;

import java.time.LocalDateTime;

public interface OrderDto {
    String getItemImageUrl();
    Long getOrderId();
    Integer getCount();
    String getItemName();
    String getCreatedAt();
    Long getItemId();
    OrderStatus getOrderStatus();
    Integer getPrice();
}

/**
 *
 * 프로젝션을 사용용
 * */