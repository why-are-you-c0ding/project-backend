package wayc.backend.shop.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.application.dto.response.show.ShowItemResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowOptionGroupResponseDto;

import java.util.List;

@NoArgsConstructor
@Getter
public class GetItemResponseDto {

    private Long itemId;
    private String itemName;
    private Long shopId;
    private String shopName;
    private List<ShowOptionGroupResponseDto> optionGroups;
    private String imageUrl;
    private String information;

    @Builder
    public GetItemResponseDto(Long itemId, String itemName, Long shopId, String shopName, List<ShowOptionGroupResponseDto> optionGroups, String imageUrl, String information) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.shopId = shopId;
        this.shopName = shopName;
        this.optionGroups = optionGroups;
        this.imageUrl = imageUrl;
        this.information = information;
    }

    public static GetItemResponseDto from(ShowItemResponseDto itemDto, List<ShowOptionGroupResponseDto> optionGroupDto) {
        return GetItemResponseDto.builder()
                .itemId(itemDto.getItemId())
                .itemName(itemDto.getItemName())
                .shopId(itemDto.getShopId())
                .shopName(itemDto.getShopName())
                .optionGroups(optionGroupDto)
                .imageUrl(itemDto.getImageUrl())
                .information(itemDto.getInformation())
                .build();
    }
}
