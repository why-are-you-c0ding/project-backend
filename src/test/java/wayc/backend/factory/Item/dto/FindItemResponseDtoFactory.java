package wayc.backend.factory.Item.dto;

import wayc.backend.shop.application.dto.response.find.FindItemDto;
import wayc.backend.shop.application.dto.response.find.FindItemResponseDto;
import wayc.backend.shop.application.dto.response.find.FindOptionGroupResponseDto;
import wayc.backend.shop.application.dto.response.find.FindOptionResponseDto;

import java.util.Arrays;
import java.util.List;

public class FindItemResponseDtoFactory {

    public static FindItemResponseDto createSuccessDto(){
        return FindItemResponseDto.of(createFindItemDto(), createFindOptionGroupResponseDto());
    }

    private static FindItemDto createFindItemDto(){
        return FindItemDto
                .builder()
                .itemId(1L)
                .itemName("맥북")
                .shopId(2L)
                .shopName("애플 스토어")
                .imageUrl("www.image.com")
                .information("information")
                .category("Electronics")
                .build();
    }

    private static List<FindOptionGroupResponseDto> createFindOptionGroupResponseDto(){

        FindOptionResponseDto optionRes1 = new FindOptionResponseDto(10L, "white", 1000);
        FindOptionResponseDto optionRes2 = new FindOptionResponseDto(11L, "black", 1000);
        FindOptionResponseDto optionRes3 = new FindOptionResponseDto(12L, "small", 1000);
        FindOptionResponseDto optionRes4 = new FindOptionResponseDto(13L, "superLarge", 1000);

        FindOptionGroupResponseDto optionGroup1 =
                new FindOptionGroupResponseDto(20L, "color", Arrays.asList(optionRes1, optionRes2));

        FindOptionGroupResponseDto optionGroup2 =
                new FindOptionGroupResponseDto(21L, "size", Arrays.asList(optionRes3, optionRes4));

        return Arrays.asList(optionGroup1, optionGroup2);
    }
}
