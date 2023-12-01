package wayc.backend.order.domain.repository.query.dto;

import lombok.Builder;
import lombok.Getter;
import wayc.backend.common.domain.Money;
import wayc.backend.order.domain.Address;
import wayc.backend.order.domain.OrderLineItemStatus;

import java.util.List;

@Getter
public class FindOrderResponseDto {

    private Long orderLineItemId;
    private Long itemId;
    private String itemName;
    private String itemImageUrl;
    private Integer count;
    private OrderLineItemStatus orderStatus;
    private String majorAddress;
    private String detailAddress;
    private String zipcode;
    private String shopName;
    private Long shopId;
    private Money price;
    private List<FindOrderOptionGroupResponseDto> orderOptionGroups;

    @Builder
    public FindOrderResponseDto(Long orderLineItemId,
                                Long itemId,
                                String itemName,
                                String itemImageUrl,
                                Integer count,
                                Money price,
                                OrderLineItemStatus orderStatus,
                                String majorAddress,
                                String detailAddress,
                                String zipcode,
                                String shopName,
                                Long shopId) {
        this.orderLineItemId = orderLineItemId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImageUrl = itemImageUrl;
        this.count = count;
        this.price = price;
        this.orderStatus = orderStatus;
        this.majorAddress = majorAddress;
        this.detailAddress = detailAddress;
        this.zipcode = zipcode;
        this.shopName = shopName;
        this.shopId = shopId;
    }

    public void setOrderOptionGroups(List<FindOrderOptionGroupResponseDto> orderOptionGroups) {
        this.orderOptionGroups = orderOptionGroups;
    }
}
