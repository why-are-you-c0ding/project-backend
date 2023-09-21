package wayc.backend.order.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import wayc.backend.order.domain.Address;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.shop.domain.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindOrderResponseDto {

    private Long orderId;
    private Long itemId;
    private String itemName;
    private String itemImageUrl;
    private Integer count;
    private OrderStatus orderStatus;
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
                                OrderStatus orderStatus,
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

    public static FindOrderResponseDto of(OrderLineItem orderLineItem, Item item, Integer price) {
        return FindOrderResponseDto.builder()
                .shopId(item.getShop().getId())
                .shopName(item.getShop().getShopName())
                .orderId(orderLineItem.getId())
                .itemId(orderLineItem.getItemId())
                .itemName(orderLineItem.getName())
                .itemImageUrl(item.getImageUrl())
                .count(orderLineItem.getCount())
                .orderStatus(orderLineItem.getOrderStatus())
                //.address(orderLineItem.getAddress())
                .price(price)
                .orderOptionGroups(
                        orderLineItem.getOrderOptionGroups()
                                .stream()
                                .map(optionGroup ->
                                        new FindOrderOptionGroupResponseDto(
                                                optionGroup.getName(),
                                                new FindOrderOptionResponseDto(
                                                        optionGroup.getOrderOptions().getOptionName()
                                                )
                                        )).collect(Collectors.toList())
                ).build();
    }
}
