package wayc.backend.shop.application.dto.response.show;

import lombok.Builder;
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
    private String imageUrl;
    private String information;
    private String category;

    public static ShowItemResponseDto from(Item item) {
        return ShowItemResponseDto.builder()
                .itemId(item.getId())
                .itemName(item.getName())
                .shopId(item.getShop().getId())
                .shopName(item.getShop().getShopName())
                .optionGroupSpecificationIdList(
                        item
                                .getOptionGroupSpecifications()
                                .stream()
                                .map(optionGroupSpecification -> optionGroupSpecification.getId())
                                .collect(Collectors.toList())
                )
                .imageUrl(item.getImageUrl())
                .information(item.getInformation())
                .category(item.getCategory())
                .build();
    }

    @Builder
    public ShowItemResponseDto(Long itemId, String itemName, Long shopId, String shopName,
                               List<Long> optionGroupSpecificationIdList, String imageUrl, String information, String category) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.shopId = shopId;
        this.shopName = shopName;
        this.optionGroupSpecificationIdList = optionGroupSpecificationIdList;
        this.imageUrl = imageUrl;
        this.information = information;
        this.category = category;
    }
}


