package wayc.backend.shop.application.dto.response.find;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.shop.exception.NotExistsOptionGroupSpecificationException;
import wayc.backend.shop.domain.Item;

import java.util.List;

@NoArgsConstructor
@Getter
public class FindItemsResponseDto {

    private Long itemId;
    private String itemName;
    private String shopName;
    private Integer basicPrice;
    private String imageUrl;
    private String category;

    public FindItemsResponseDto(Long itemId, String itemName, String shopName, Integer basicPrice, String imageUrl, String category) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.shopName = shopName;
        this.basicPrice = basicPrice;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public static FindItemsResponseDto of(Item item) {
        return new FindItemsResponseDto(
                item.getId(),
                item.getName(),
                item.getShop().getShopName(),
                findBasicGroupPrice(item.getOptionGroups()),
                item.getImageUrl(),
                item.getCategory()
        );
    }

    private static Integer findBasicGroupPrice(List<OptionGroup> optionGroups){
        return optionGroups
                .stream()
                .map(optionGroup -> optionGroup.getBasicPrice())
                .filter(price -> price != -1)
                .findFirst()
                .orElseThrow(NotExistsOptionGroupSpecificationException::new);
    }
}
