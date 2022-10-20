package wayc.backend.factory.order;

import wayc.backend.order.application.dto.response.*;
import wayc.backend.order.domain.OrderStatus;

import java.util.Arrays;

public class ShowTotalOrderResponseDtoFactory {


    public static ShowTotalOrderResponseDto createSuccessCaseForCustomer(){

        ShowOrderOptionGroupResponseDto optionGroup_size = new ShowOrderOptionGroupResponseDto("size", new ShowOrderOptionResponseDto("Large"));
        ShowOrderOptionGroupResponseDto optionGroup_color = new ShowOrderOptionGroupResponseDto("color", new ShowOrderOptionResponseDto("Black"));

        ShowOrdersForCustomerResponseDto order_1 = ShowOrdersForCustomerResponseDto.builder()
                .itemImageUrl("www.image.com")
                .shopName("멋쟁이 옷 가게")
                .itemName("멋쟁이 옷")
                .orderOptionGroups(Arrays.asList(optionGroup_color, optionGroup_size))
                .count(2)
                .shopId(1L)
                .itemId(2L)
                .orderId(4L)
                .orderStatus(OrderStatus.ONGOING)
                .build();


        ShowOrderOptionGroupResponseDto optionGroup_ram = new ShowOrderOptionGroupResponseDto("Ram", new ShowOrderOptionResponseDto("16GB"));
        ShowOrderOptionGroupResponseDto optionGroup_ssd = new ShowOrderOptionGroupResponseDto("SSD", new ShowOrderOptionResponseDto("512GB"));

        ShowOrdersForCustomerResponseDto order_2 = ShowOrdersForCustomerResponseDto.builder()
                .itemImageUrl("www.image.com")
                .shopName("맥 스토어")
                .itemName("맥북")
                .orderOptionGroups(Arrays.asList(optionGroup_color, optionGroup_size))
                .count(2)
                .shopId(2L)
                .itemId(3L)
                .orderId(7L)
                .orderStatus(OrderStatus.COMPLICATED)
                .build();

        return new ShowTotalOrderResponseDto(true, Arrays.asList(order_1, order_2));
    }


    public static ShowTotalOrderResponseDto createSuccessCaseForSeller(){

        ShowOrdersForSellerResponseDto dto_1 =
                new ShowOrdersForSellerResponseDto("www.image.com", 1L, 2, "멋진 옷", "2022-10-19 16:03:08", 39L, OrderStatus.ONGOING);
        ShowOrdersForSellerResponseDto dto_2 =
                new ShowOrdersForSellerResponseDto("www.image.com", 2L, 3, "닭가슴살 패키지", "2022-10-19 16:03:08", 40L, OrderStatus.ONGOING);
        ShowOrdersForSellerResponseDto dto_3 =
                new ShowOrdersForSellerResponseDto("www.image.com", 3L, 4, "소시지", "2022-10-19 16:03:08", 32L, OrderStatus.ONGOING);
        ShowOrdersForSellerResponseDto dto_4 =
                new ShowOrdersForSellerResponseDto("www.image.com", 4L, 1, "맥북", "2022-10-19 16:03:08", 42L, OrderStatus.COMPLICATED);


        return new ShowTotalOrderResponseDto(true, Arrays.asList(dto_1, dto_2, dto_3, dto_4));
    }
}
