package wayc.backend.order.domain.repository.query.dto;

import lombok.Builder;
import lombok.Getter;
import wayc.backend.order.domain.Address;
import wayc.backend.order.domain.OrderLineItemStatus;

import java.util.List;

@Getter
public class FindOrderResponseDto {

    private Long orderId;
    private Long itemId;
    private String itemName;
    private String itemImageUrl;
    private Integer count;
    private OrderLineItemStatus orderStatus;
    private Address address;
    private String shopName;
    private Long shopId;
    private Integer price;
    private List<FindOrderOptionGroupResponseDto> orderOptionGroups;

    @Builder
    public FindOrderResponseDto(Long orderId,
                                Long itemId,
                                String itemName,
                                String itemImageUrl,
                                Integer count,
                                Integer price,
                                OrderLineItemStatus orderStatus,
                                Address address,
                                String shopName,
                                Long shopId,
                                List<FindOrderOptionGroupResponseDto> orderOptionGroups) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImageUrl = itemImageUrl;
        this.count = count;
        this.orderStatus = orderStatus;
        this.address = address;
        this.shopName = shopName;
        this.shopId = shopId;
        this.orderOptionGroups = orderOptionGroups;
        this.price = price;
    }
}
