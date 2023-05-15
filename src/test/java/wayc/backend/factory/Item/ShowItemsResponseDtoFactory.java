package wayc.backend.factory.Item;

import wayc.backend.shop.application.dto.response.find.FindItemsResponseDto;

import java.util.Arrays;
import java.util.List;

public class ShowItemsResponseDtoFactory {

    public static List<FindItemsResponseDto> createSuccessCaseDto(){
        FindItemsResponseDto item_1 = new FindItemsResponseDto(1L,"맥북", "애플 스토어", 1000000 ,"www.image.com", "Electronics");
        FindItemsResponseDto item_2 = new FindItemsResponseDto(2L,"삼성 이어폰", "삼성 스토어", 100000, "www.image.com", "Electronics");
        FindItemsResponseDto item_3 = new FindItemsResponseDto(3L, "단백질 파우더", "헬스 스토어", 10000, "www.image.com", "Sports & Outdoors");
        FindItemsResponseDto item_4 = new FindItemsResponseDto(4L, "맛있는 과자", "잘나가는 과자집", 1000, "www.image.com" , "Food");
        FindItemsResponseDto item_5 = new FindItemsResponseDto(5L, "비둘기", "에버랜드", 999, "www.image.com" ,"Pets");

        return Arrays.asList(item_1, item_2, item_3, item_4, item_5);
    }
}
