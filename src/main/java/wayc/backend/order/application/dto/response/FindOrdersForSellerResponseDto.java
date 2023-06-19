package wayc.backend.order.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.order.domain.repository.OrderDto;

@NoArgsConstructor
@Getter
public class FindOrdersForSellerResponseDto {

    private String itemImageUrl;
    private Long orderId;
    private Integer count;
    private String itemName;
    private String createdAt;
    private Long itemId;
    private OrderStatus orderStatus;
    private Integer price;

    public static FindOrdersForSellerResponseDto of(OrderDto dto){
        return new FindOrdersForSellerResponseDto(
                dto.getItemImageUrl(),
                dto.getOrderId(),
                dto.getCount(),
                dto.getItemName(),
                dto.getCreatedAt(),
                dto.getItemId(),
                dto.getOrderStatus(),
                dto.getPrice()
        );
    }

    public FindOrdersForSellerResponseDto(String itemImageUrl,
                                          Long orderId,
                                          Integer count,
                                          String itemName,
                                          String createdAt,
                                          Long itemId,
                                          OrderStatus orderStatus,
                                          Integer price) {
        this.itemImageUrl = itemImageUrl;
        this.orderId = orderId;
        this.count = count;
        this.itemName = itemName;
        this.createdAt = createdAt;
        this.itemId = itemId;
        this.orderStatus = orderStatus;
        this.price = price;
    }

    public Long getItemId() {
        return itemId;
    }
}