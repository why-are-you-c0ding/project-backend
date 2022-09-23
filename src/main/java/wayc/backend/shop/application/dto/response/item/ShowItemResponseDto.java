package wayc.backend.shop.application.dto.response.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.domain.Item;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ShowItemResponseDto {

    private Long itemId;
    private String itemName;
    private Long shopId;
    private String shopName;
    private List<Long> optionGroupSpecificationIdList;

    public ShowItemResponseDto(Item item) {
        this.itemId = item.getId();
        this.itemName = item.getName();
        this.shopId = item.getId();
        this.shopName = item.getShop().getShopName();
        this.optionGroupSpecificationIdList = item
                .getOptionGroupSpecifications()
                .stream()
                .map(optionGroupSpecification -> optionGroupSpecification.getId())
                .collect(Collectors.toList());
    }
}


