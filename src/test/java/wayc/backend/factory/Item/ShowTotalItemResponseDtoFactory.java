package wayc.backend.factory.Item;

import wayc.backend.shop.application.dto.response.find.FindItemsResponseDto;
import wayc.backend.shop.application.dto.response.find.FindPagingItemResponseDto;

import java.util.Arrays;
import java.util.List;

public class ShowTotalItemResponseDtoFactory {


    public static FindPagingItemResponseDto createSuccessCaseDto(){
        FindItemsResponseDto item_1 = new FindItemsResponseDto(1L,"맥북", "애플 스토어", 1000000 ,"www.image.com", "Electronics");
        FindItemsResponseDto item_2 = new FindItemsResponseDto(2L,"아이폰", "애플 스토어", 100000, "www.image.com", "Electronics");
        FindItemsResponseDto item_3 = new FindItemsResponseDto(3L, "아이패드", "애플 스토어", 10000, "www.image.com", "Electronics");
        FindItemsResponseDto item_4 = new FindItemsResponseDto(4L, "에어팟", "애플 스토어", 1000, "www.image.com" , "Electronics");
        FindItemsResponseDto item_5 = new FindItemsResponseDto(5L, "마이맥", "애플 스토어", 999, "www.image.com" , "Electronics");

        List<FindItemsResponseDto> list = Arrays.asList(item_1, item_2, item_3, item_4, item_5);

        return new FindPagingItemResponseDto(true, list);
    }
}
