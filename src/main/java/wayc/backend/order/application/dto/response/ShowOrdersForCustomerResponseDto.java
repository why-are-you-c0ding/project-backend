package wayc.backend.order.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.shop.domain.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ShowOrdersForCustomerResponseDto {

    private String itemImageUrl;
    private String shopName;
    private String itemName;
    private List<ShowOrderOptionGroupResponseDto> orderOptionGroups = new ArrayList<>();
    private Integer count;
    private Long shopId;
    private Long itemId;
    private Long orderId;
    private OrderStatus orderStatus;

    @Builder
    public ShowOrdersForCustomerResponseDto(String itemImageUrl, String shopName, String itemName, List<ShowOrderOptionGroupResponseDto> orderOptionGroups, Integer count, Long shopId, Long itemId, Long orderId, OrderStatus orderStatus) {
        this.itemImageUrl = itemImageUrl;
        this.shopName = shopName;
        this.itemName = itemName;
        this.orderOptionGroups = orderOptionGroups;
        this.count = count;
        this.shopId = shopId;
        this.itemId = itemId;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }



    public static ShowOrdersForCustomerResponseDto of(Order order, Item item) {
        return ShowOrdersForCustomerResponseDto
                .builder()
                .itemImageUrl(item.getImageUrl())
                .shopName(item.getShop().getShopName())
                .count(order.getCount())
                .shopId(item.getShop().getId())
                .itemId(item.getId())
                .itemName(item.getName())
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .orderOptionGroups(
                        order.getOrderOptionGroups()
                                .stream()
                                .map(orderOptionGroup ->
                                        new ShowOrderOptionGroupResponseDto(
                                                orderOptionGroup.getName(),
                                                new ShowOrderOptionResponseDto(
                                                        orderOptionGroup.getOrderOptions().getOptionName()
                                                )
                                        )
                                        )
                                .collect(Collectors.toList())
                )
                .build();
    }
}
