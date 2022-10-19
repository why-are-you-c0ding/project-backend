package wayc.backend.factory.Item;

import wayc.backend.shop.application.dto.response.show.ShowItemsResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowTotalItemResponseDto;

import java.util.Arrays;
import java.util.List;

public class ShowTotalItemResponseDtoFactory {


    public static ShowTotalItemResponseDto createSuccessCaseDto(){
        ShowItemsResponseDto item_1 = new ShowItemsResponseDto(1L,"맥북", "애플 스토어", 1000000 ,"www.image.com");
        ShowItemsResponseDto item_2 = new ShowItemsResponseDto(2L,"아이폰", "애플 스토어", 100000, "www.image.com");
        ShowItemsResponseDto item_3 = new ShowItemsResponseDto(3L, "아이패드", "애플 스토어", 10000, "www.image.com");
        ShowItemsResponseDto item_4 = new ShowItemsResponseDto(4L, "에어팟", "애플 스토어", 1000, "www.image.com" );
        ShowItemsResponseDto item_5 = new ShowItemsResponseDto(5L, "마이맥", "애플 스토어", 999, "www.image.com" );

        List<ShowItemsResponseDto> list = Arrays.asList(item_1, item_2, item_3, item_4, item_5);

        return new ShowTotalItemResponseDto(true, list);
    }
}
