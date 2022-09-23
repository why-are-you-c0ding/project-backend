package wayc.backend.factory.Item;

import wayc.backend.shop.application.dto.response.show.ShowItemResponseDto;

import java.util.Arrays;

public class ShowItemResponseDtoFactory {
    public static ShowItemResponseDto createSuccessCaseDto(){
        return ShowItemResponseDto
                .builder()
                .itemId(1L)
                .itemName("itemName")
                .shopId(2L)
                .shopName("shopName")
                .optionGroupSpecificationIdList(Arrays.asList(3L, 4L))
                .build();
    }
}
