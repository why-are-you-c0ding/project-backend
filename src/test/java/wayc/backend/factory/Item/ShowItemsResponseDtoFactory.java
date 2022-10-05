package wayc.backend.factory.Item;

import wayc.backend.shop.application.dto.response.show.ShowItemsResponseDto;

import java.util.Arrays;
import java.util.List;

public class ShowItemsResponseDtoFactory {

    public static List<ShowItemsResponseDto> createSuccessCaseDto(){
        ShowItemsResponseDto item_1 = new ShowItemsResponseDto(1L,"맥북", "애플 스토어", 1000000 );
        ShowItemsResponseDto item_2 = new ShowItemsResponseDto(2L,"삼성 이어폰", "삼성 스토어", 100000 );
        ShowItemsResponseDto item_3 = new ShowItemsResponseDto(3L, "단백질 파우더", "헬스 스토어", 10000 );
        ShowItemsResponseDto item_4 = new ShowItemsResponseDto(4L, "맛있는 과자", "잘나가는 과자집", 1000 );
        ShowItemsResponseDto item_5 = new ShowItemsResponseDto(5L, "비둘기", "에버랜드", 999 );

        return Arrays.asList(item_1, item_2, item_3, item_4, item_5);
    }
}
