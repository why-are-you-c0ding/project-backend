package wayc.backend.shop.application.dto.response.find;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.domain.Item;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class FindItemDto {

    private Long itemId;
    private String itemName;
    private Long shopId;
    private String shopName;
    private String imageUrl;
    private String information;
    private String category;

    public static FindItemDto from(Item item) {
        return FindItemDto.builder()
                .itemId(item.getId())
                .itemName(item.getName())
                .shopId(item.getShop().getId())
                .shopName(item.getShop().getName())
                .imageUrl(item.getImageUrl())
                .information(item.getInformation())
                .category(item.getCategory())
                .build();
    }

    @Builder
    public FindItemDto(Long itemId,
                       String itemName,
                       Long shopId,
                       String shopName,
                       String imageUrl,
                       String information,
                       String category) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.shopId = shopId;
        this.shopName = shopName;
        this.imageUrl = imageUrl;
        this.information = information;
        this.category = category;
    }
}


