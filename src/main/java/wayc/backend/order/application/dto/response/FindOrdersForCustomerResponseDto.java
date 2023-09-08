package wayc.backend.order.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.shop.domain.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class FindOrdersForCustomerResponseDto {

    private String itemImageUrl;
    private String shopName;
    private String itemName;
    private List<FindOrderOptionGroupResponseDto> orderOptionGroups = new ArrayList<>();
    private Integer count;
    private Long shopId;
    private Long itemId;
    private Long orderId;
    private OrderStatus orderStatus;
    private Integer price;

    @Builder
    public FindOrdersForCustomerResponseDto(String itemImageUrl, String shopName, String itemName, List<FindOrderOptionGroupResponseDto> orderOptionGroups, Integer count, Long shopId, Long itemId, Long orderId, OrderStatus orderStatus, Integer price) {
        this.itemImageUrl = itemImageUrl;
        this.shopName = shopName;
        this.itemName = itemName;
        this.orderOptionGroups = orderOptionGroups;
        this.count = count;
        this.shopId = shopId;
        this.itemId = itemId;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.price = price;
    }



    public static FindOrdersForCustomerResponseDto of(OrderLineItem order, Item item, Integer price) {
        return FindOrdersForCustomerResponseDto
                .builder()
                .itemImageUrl(item.getImageUrl())
                .shopName(item.getShop().getShopName())
                .count(order.getCount())
                .shopId(item.getShop().getId())
                .itemId(item.getId())
                .itemName(item.getName())
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .price(price)
                .orderOptionGroups(
                        order.getOrderOptionGroups()
                                .stream()
                                .map(orderOptionGroup ->
                                        new FindOrderOptionGroupResponseDto(
                                                orderOptionGroup.getName(),
                                                new FindOrderOptionResponseDto(
                                                        orderOptionGroup.getOrderOptions().getOptionName()
                                                )
                                        )
                                        )
                                .collect(Collectors.toList())
                )
                .build();
    }
}
