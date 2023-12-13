package wayc.backend.shop.application.dto.response.find;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class FindItemResponseDto {

    private Long itemId;
    private String itemName;
    private Long shopId;
    private String shopName;
    private List<FindOptionGroupResponseDto> optionGroups;
    private String imageUrl;
    private String information;
    private String category;

    @Builder
    public FindItemResponseDto(Long itemId,
                               String itemName,
                               Long shopId,
                               String shopName,
                               List<FindOptionGroupResponseDto> optionGroups,
                               String imageUrl,
                               String information,
                               String category) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.shopId = shopId;
        this.shopName = shopName;
        this.optionGroups = optionGroups;
        this.imageUrl = imageUrl;
        this.information = information;
        this.category = category;
    }

    public static FindItemResponseDto of(FindItemDto itemDto,
                                         List<FindOptionGroupResponseDto> optionGroupDto) {
        return FindItemResponseDto.builder()
                .itemId(itemDto.getItemId())
                .itemName(itemDto.getItemName())
                .shopId(itemDto.getShopId())
                .shopName(itemDto.getShopName())
                .optionGroups(optionGroupDto)
                .imageUrl(itemDto.getImageUrl())
                .information(itemDto.getInformation())
                .category(itemDto.getCategory())
                .build();
    }
}
