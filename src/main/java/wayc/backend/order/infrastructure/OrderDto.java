package wayc.backend.order.infrastructure;

import java.time.LocalDateTime;

public interface OrderDto {
    String getItemImageUrl();
    Long getOrderId();
    Integer getCount();
    String getItemName();
    String getCreatedAt();
    Long getItemId();
}

/**
 *
 * 프로젝션을 사용용
 * */