package wayc.backend.order.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.domain.OrderStatus;

@NoArgsConstructor
@Getter
public class ShowOrdersForSellerResponseDto {

    private String itemImageUrl;
    private Long orderId;
    private Integer count;
    private String itemName;
    private String createdAt;
    private Long itemId;
    private OrderStatus orderStatus;

    public ShowOrdersForSellerResponseDto(String itemImageUrl, Long orderId, Integer count, String itemName, String createdAt, Long itemId, OrderStatus orderStatus) {
        this.itemImageUrl = itemImageUrl;
        this.orderId = orderId;
        this.count = count;
        this.itemName = itemName;
        this.createdAt = createdAt;
        this.itemId = itemId;
        this.orderStatus = orderStatus;
    }

    public Long getItemId() {
        return itemId;
    }
}
