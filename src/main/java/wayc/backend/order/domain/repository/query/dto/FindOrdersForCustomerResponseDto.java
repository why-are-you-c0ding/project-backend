package wayc.backend.order.domain.repository.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import wayc.backend.common.domain.Money;
import wayc.backend.order.domain.OrderLineItemStatus;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
public class FindOrdersForCustomerResponseDto {

    private String itemImageUrl;
    private String shopName;
    private String itemName;
    private Integer count;
    private Long shopId;
    private Long itemId;
    private Long orderLineItemId;
    private OrderLineItemStatus orderStatus;
    private Integer price;
    private List<FindOrderOptionGroupResponseDto> orderOptionGroups = new ArrayList<>();

    @Builder
    @QueryProjection
    public FindOrdersForCustomerResponseDto(String itemImageUrl,
                                            String shopName,
                                            String itemName,
                                            Integer count,
                                            Long shopId,
                                            Long itemId,
                                            Long orderLineItemId,
                                            OrderLineItemStatus orderStatus,
                                            Money price,
                                            List<FindOrderOptionGroupResponseDto> orderOptionGroups) {
        this.itemImageUrl = itemImageUrl;
        this.shopName = shopName;
        this.itemName = itemName;
        this.count = count;
        this.shopId = shopId;
        this.itemId = itemId;
        this.orderLineItemId = orderLineItemId;
        this.orderStatus = orderStatus;
        this.price = price.intValue();
        this.orderOptionGroups = orderOptionGroups;
    }
}
