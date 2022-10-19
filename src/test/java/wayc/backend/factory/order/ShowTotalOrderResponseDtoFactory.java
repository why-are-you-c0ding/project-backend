package wayc.backend.factory.order;

import wayc.backend.order.application.dto.response.ShowOrderOptionGroupResponseDto;
import wayc.backend.order.application.dto.response.ShowOrderOptionResponseDto;
import wayc.backend.order.application.dto.response.ShowOrdersResponseDto;
import wayc.backend.order.application.dto.response.ShowTotalOrderResponseDto;
import wayc.backend.order.presentation.dto.request.PostAddressRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderOptionGroupRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderOptionRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderRequestDto;

import java.util.Arrays;
import java.util.List;

public class ShowTotalOrderResponseDtoFactory {


    public static ShowTotalOrderResponseDto createSuccessCase(){

        ShowOrderOptionGroupResponseDto optionGroup_size = new ShowOrderOptionGroupResponseDto("size", new ShowOrderOptionResponseDto("Large"));
        ShowOrderOptionGroupResponseDto optionGroup_color = new ShowOrderOptionGroupResponseDto("color", new ShowOrderOptionResponseDto("Black"));

        ShowOrdersResponseDto order_1 = ShowOrdersResponseDto.builder()
                .itemImageUrl("www.image.com")
                .shopName("멋쟁이 옷 가게")
                .itemName("멋쟁이 옷")
                .orderOptionGroups(Arrays.asList(optionGroup_color, optionGroup_size))
                .count(2)
                .shopId(1L)
                .itemId(2L)
                .orderId(4L)
                .build();


        ShowOrderOptionGroupResponseDto optionGroup_ram = new ShowOrderOptionGroupResponseDto("Ram", new ShowOrderOptionResponseDto("16GB"));
        ShowOrderOptionGroupResponseDto optionGroup_ssd = new ShowOrderOptionGroupResponseDto("SSD", new ShowOrderOptionResponseDto("512GB"));

        ShowOrdersResponseDto order_2 = ShowOrdersResponseDto.builder()
                .itemImageUrl("www.image.com")
                .shopName("맥 스토어")
                .itemName("맥북")
                .orderOptionGroups(Arrays.asList(optionGroup_color, optionGroup_size))
                .count(2)
                .shopId(2L)
                .itemId(3L)
                .orderId(7L)
                .build();

        return new ShowTotalOrderResponseDto(true, Arrays.asList(order_1, order_2));
    }
}
