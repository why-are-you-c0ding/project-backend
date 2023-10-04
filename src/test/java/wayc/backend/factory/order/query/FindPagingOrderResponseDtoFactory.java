package wayc.backend.factory.order.query;

import wayc.backend.order.domain.OrderLineItemStatus;
import wayc.backend.order.domain.repository.query.dto.*;

import java.util.Arrays;

public class FindPagingOrderResponseDtoFactory {


    public static FindPagingOrderResponseDto createSuccessCaseForCustomer(){

        FindOrderOptionGroupResponseDto optionGroup_size = new FindOrderOptionGroupResponseDto("size", "Large");
        FindOrderOptionGroupResponseDto optionGroup_color = new FindOrderOptionGroupResponseDto("color", "Black");

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
                .orderStatus(OrderLineItemStatus.ORDER_ACCEPTED)
                .build();


        FindOrderOptionGroupResponseDto optionGroup_ram = new FindOrderOptionGroupResponseDto("Ram", "16GB");
        FindOrderOptionGroupResponseDto optionGroup_ssd = new FindOrderOptionGroupResponseDto("SSD", "512GB");

        FindOrdersForCustomerResponseDto order_2 = FindOrdersForCustomerResponseDto.builder()
                .itemImageUrl("www.image.com")
                .shopName("맥 스토어")
                .itemName("맥북")
                .orderOptionGroups(Arrays.asList(optionGroup_ram, optionGroup_ssd))
                .count(2)
                .shopId(2L)
                .price(20000)
                .itemId(3L)
                .orderId(7L)
                .orderStatus(OrderLineItemStatus.PAYMENT_COMPLETED)
                .build();

        return new FindPagingOrderResponseDto(true, Arrays.asList(order_1, order_2));
    }


    public static FindPagingOrderResponseDto createSuccessCaseForSeller(){
        FindOrderOptionGroupResponseDto optionGroup_ram = new FindOrderOptionGroupResponseDto("Ram", "16GB");
        FindOrderOptionGroupResponseDto optionGroup_ssd = new FindOrderOptionGroupResponseDto("SSD", "512GB");

        FindOrdersForSellerResponseDto dto_1 =
                FindOrdersForSellerResponseDto.builder()
                        .itemImageUrl("www.image.com")
                        .shopName("맥 스토어")
                        .itemName("맥북")
                        .orderOptionGroups(Arrays.asList(optionGroup_ram, optionGroup_ssd))
                        .count(2)
                        .shopId(2L)
                        .price(20000)
                        .itemId(3L)
                        .orderId(7L)
                        .orderStatus(OrderLineItemStatus.PAYMENT_COMPLETED)
                        .build();


        FindOrdersForSellerResponseDto dto_2 =
                FindOrdersForSellerResponseDto.builder()
                        .itemImageUrl("www.image.com")
                        .shopName("맥 스토어")
                        .itemName("맥북")
                        .orderOptionGroups(Arrays.asList(optionGroup_ram, optionGroup_ssd))
                        .count(2)
                        .shopId(2L)
                        .price(20000)
                        .itemId(3L)
                        .orderId(8L)
                        .orderStatus(OrderLineItemStatus.PAYMENT_COMPLETED)
                        .build();

        return new FindPagingOrderResponseDto(true, Arrays.asList(dto_1, dto_2));
    }
}
