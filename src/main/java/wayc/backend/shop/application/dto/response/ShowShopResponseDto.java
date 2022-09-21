package wayc.backend.shop.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ShowShopResponseDto {

    private Long id;

    //private Long ownerId;

    private String shopName;

    private List<Item> items = new ArrayList<>();

    @Builder
    public ShowShopResponseDto(Long id, String shopName, List<Item> items) {
        this.id = id;
        this.shopName = shopName;
        this.items = items;
    }

    @Builder
    public static ShowShopResponseDto of(Shop shop) {
        return ShowShopResponseDto
                .builder()
                .id(shop.getId())
                .shopName(shop.getShopName())
                .items(shop.getItems())
                .build();
    }
}
