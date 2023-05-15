package wayc.backend.factory.Item;

import wayc.backend.shop.application.dto.response.find.FindItemDto;

import java.util.Arrays;

public class ShowItemResponseDtoFactory {
    public static FindItemDto createSuccessCaseDto(){
        return FindItemDto
                .builder()
                .itemId(1L)
                .itemName("맥북")
                .shopId(2L)
                .shopName("애플 스토어")
                .optionGroupSpecificationIdList(Arrays.asList(3L, 4L))
                .imageUrl("www.image.com")
                .information("information")
                .category("Electronics")
                .build();
    }
}
