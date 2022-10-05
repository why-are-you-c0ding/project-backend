package wayc.backend.shop.application.dto.response.show;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.exception.shop.NotExistsOptionGroupSpecificationException;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.OptionGroupSpecification;

import java.util.List;

@NoArgsConstructor
@Getter
public class ShowItemsResponseDto {

    private Long itemId;
    private String itemName;
    private String shopName;
    private Integer basicPrice;

    public ShowItemsResponseDto(Long itemId, String itemName, String shopName, Integer basicPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.shopName = shopName;
        this.basicPrice = basicPrice;
    }

    public static ShowItemsResponseDto of(Item item) {
        return new ShowItemsResponseDto(
                item.getId(),
                item.getName(),
                item.getShop().getShopName(),
                findBasicGroupPrice(item.getOptionGroupSpecifications()
                ));
    }

    private static Integer findBasicGroupPrice(List<OptionGroupSpecification> optionGroups){
        return optionGroups
                .stream()
                .map(optionGroup -> optionGroup.getBasicPrice())
                .filter(price -> price != -1)
                .findFirst()
                .orElseThrow(NotExistsOptionGroupSpecificationException::new);
    }
}
