package wayc.backend.factory.order.query;

import wayc.backend.order.domain.Address;
import wayc.backend.order.domain.OrderLineItemStatus;
import wayc.backend.order.domain.repository.query.dto.FindOrderOptionGroupResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindOrderOptionResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindOrderResponseDto;

import java.util.Arrays;

public class FindOrderResponseDtoFactory {

    public static FindOrderResponseDto createSuccessCase(){

        FindOrderOptionGroupResponseDto optionGroup_1 = new FindOrderOptionGroupResponseDto(
                "ram", "16GB"
        );

        FindOrderOptionGroupResponseDto optionGroup_2 = new FindOrderOptionGroupResponseDto(
                "ssd", "512GB"
        );

        return  FindOrderResponseDto
                .builder()
                .orderId(1L)
                .itemId(2L)
                .itemName("맥북")
                .itemImageUrl("www.google.com")
                .count(3)
                .orderStatus(OrderLineItemStatus.ORDER_ACCEPTED)
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
