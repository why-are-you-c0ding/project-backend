package wayc.backend.shop.application.dto.response.find;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class FindShopResponseDto {

    private Long id;

    //private Long ownerId;

    private String shopName;

    private List<Item> items = new ArrayList<>();

    @Builder
    public FindShopResponseDto(Long id, String shopName, List<Item> items) {
        this.id = id;
        this.shopName = shopName;
        this.items = items;
    }

    @Builder
    public static FindShopResponseDto of(Shop shop) {
        return FindShopResponseDto
                .builder()
                .id(shop.getId())
                .shopName(shop.getName())
                .items(shop.getItems())
                .build();
    }
}
