package wayc.backend.order.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ShowOrdersForSellerResponseDto {

    private String itemImageUrl;
    private Long orderId;
    private Integer count;
    private String itemName;
    private String createdAt;
    private Long itemId;

    public ShowOrdersForSellerResponseDto(String itemImageUrl, Long orderId, Integer count, String itemName, String createdAt, Long itemId) {
        this.itemImageUrl = itemImageUrl;
        this.orderId = orderId;
        this.count = count;
        this.itemName = itemName;
        this.createdAt = createdAt;
        this.itemId = itemId;
    }

    public Long getItemId() {
        return itemId;
    }
}
