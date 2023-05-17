package wayc.backend.factory.order;

import wayc.backend.order.application.dto.response.*;
import wayc.backend.order.domain.OrderStatus;

import java.util.Arrays;

public class ShowTotalOrderResponseDtoFactory {


    public static FindPagingOrderResponseDto createSuccessCaseForCustomer(){

        FindOrderOptionGroupResponseDto optionGroup_size = new FindOrderOptionGroupResponseDto("size", new FindOrderOptionResponseDto("Large"));
        FindOrderOptionGroupResponseDto optionGroup_color = new FindOrderOptionGroupResponseDto("color", new FindOrderOptionResponseDto("Black"));

        FindOrdersForCustomerResponseDto order_1 = FindOrdersForCustomerResponseDto.builder()
                .itemImageUrl("www.image.com")
                .shopName("멋쟁이 옷 가게")
                .itemName("멋쟁이 옷")
                .orderOptionGroups(Arrays.asList(optionGroup_color, optionGroup_size))
                .count(2)
                .shopId(1L)
                .price(100000)
                .itemId(2L)
                .orderId(4L)
                .orderStatus(OrderStatus.ONGOING)
                .build();


        FindOrderOptionGroupResponseDto optionGroup_ram = new FindOrderOptionGroupResponseDto("Ram", new FindOrderOptionResponseDto("16GB"));
        FindOrderOptionGroupResponseDto optionGroup_ssd = new FindOrderOptionGroupResponseDto("SSD", new FindOrderOptionResponseDto("512GB"));

        FindOrdersForCustomerResponseDto order_2 = FindOrdersForCustomerResponseDto.builder()
                .itemImageUrl("www.image.com")
                .shopName("맥 스토어")
                .itemName("맥북")
                .orderOptionGroups(Arrays.asList(optionGroup_color, optionGroup_size))
                .count(2)
                .shopId(2L)
                .price(20000)
                .itemId(3L)
                .orderId(7L)
                .orderStatus(OrderStatus.COMPLETED)
                .build();

        return new FindPagingOrderResponseDto(true, Arrays.asList(order_1, order_2));
    }


    public static FindPagingOrderResponseDto createSuccessCaseForSeller(){

        FindOrdersForSellerResponseDto dto_1 =
                new FindOrdersForSellerResponseDto("www.image.com", 1L, 2, "멋진 옷", "2022-10-19 16:03:08", 39L, OrderStatus.ONGOING, 5000);
        FindOrdersForSellerResponseDto dto_2 =
                new FindOrdersForSellerResponseDto("www.image.com", 2L, 3, "닭가슴살 패키지", "2022-10-19 16:03:08", 40L, OrderStatus.ONGOING, 4000);
        FindOrdersForSellerResponseDto dto_3 =
                new FindOrdersForSellerResponseDto("www.image.com", 3L, 4, "소시지", "2022-10-19 16:03:08", 32L, OrderStatus.ONGOING, 4000);
        FindOrdersForSellerResponseDto dto_4 =
                new FindOrdersForSellerResponseDto("www.image.com", 4L, 1, "맥북", "2022-10-19 16:03:08", 42L, OrderStatus.COMPLETED, 100000);


        return new FindPagingOrderResponseDto(true, Arrays.asList(dto_1, dto_2, dto_3, dto_4));
    }
}
