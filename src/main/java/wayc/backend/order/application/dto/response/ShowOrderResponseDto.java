package wayc.backend.order.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import wayc.backend.order.domain.Address;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.shop.domain.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ShowOrderResponseDto {

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

    private List<ShowOrderOptionGroupResponseDto> orderOptionGroups = new ArrayList<>();

    @Builder
    public ShowOrderResponseDto(Long orderId, Long itemId, String itemName, String itemImageUrl, Integer count, Integer price,
                                OrderStatus orderStatus, Address address, String shopName, Long shopId, List<ShowOrderOptionGroupResponseDto> orderOptionGroups) {
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

    public static ShowOrderResponseDto of(Order order, Item item, Integer price) {
        return ShowOrderResponseDto.builder()
                .shopId(item.getShop().getId())
                .shopName(item.getShop().getShopName())
                .orderId(order.getId())
                .itemId(order.getItemId())
                .itemName(order.getName())
                .itemImageUrl(item.getImageUrl())
                .count(order.getCount())
                .orderStatus(order.getOrderStatus())
                .address(order.getAddress())
                .price(price)
                .orderOptionGroups(
                        order.getOrderOptionGroups()
                                .stream()
                                .map(optionGroup ->
                                        new ShowOrderOptionGroupResponseDto(
                                                optionGroup.getName(),
                                                new ShowOrderOptionResponseDto(
                                                        optionGroup.getOrderOptions().getOptionName()
                                                )
                                        )).collect(Collectors.toList())
                ).build();
    }
}
