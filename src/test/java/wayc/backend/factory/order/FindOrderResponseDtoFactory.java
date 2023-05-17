package wayc.backend.factory.order;

import wayc.backend.order.application.dto.response.*;
import wayc.backend.order.domain.Address;
import wayc.backend.order.domain.OrderStatus;

import java.util.Arrays;

public class FindOrderResponseDtoFactory {

    public static FindOrderResponseDto createSuccessCase(){

        FindOrderOptionGroupResponseDto optionGroup_1 = new FindOrderOptionGroupResponseDto(
                "ram", new FindOrderOptionResponseDto("16GB")
        );

        FindOrderOptionGroupResponseDto optionGroup_2 = new FindOrderOptionGroupResponseDto(
                "ssd", new FindOrderOptionResponseDto("512GB")
        );

        return  FindOrderResponseDto
                .builder()
                .orderId(1L)
                .itemId(2L)
                .itemName("맥북")
                .itemImageUrl("www.google.com")
                .count(3)
                .orderStatus(OrderStatus.ONGOING)
                .address(
                        new Address("서울 중랑구 동일로 756", "999동 999호", "02020")
                )
                .orderOptionGroups(
                        Arrays.asList(optionGroup_1, optionGroup_2)
                )
                .shopId(7L)
                .shopName("멋쟁이들의 가게")
                .price(10000)
                .build();
    }
}
