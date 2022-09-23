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
    private List<ShowOptionGroupResponseDto> optionGroup;

    @Builder
    public GetItemResponseDto(Long itemId, String itemName, Long shopId, String shopName, List<ShowOptionGroupResponseDto> optionGroup) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.shopId = shopId;
        this.shopName = shopName;
        this.optionGroup = optionGroup;
    }

    public static GetItemResponseDto from(ShowItemResponseDto itemDto, List<ShowOptionGroupResponseDto> optionGroupDto) {
        return GetItemResponseDto.builder()
                .itemId(itemDto.getItemId())
                .itemName(itemDto.getItemName())
                .shopId(itemDto.getShopId())
                .shopName(itemDto.getShopName())
                .optionGroup(optionGroupDto)
                .build();
    }
}
