package wayc.backend.factory.order.query;

import wayc.backend.common.domain.Money;
import wayc.backend.order.domain.Address;
import wayc.backend.order.domain.OrderLineItemStatus;
import wayc.backend.order.domain.repository.query.dto.FindOrderOptionGroupResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindOrderOptionResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindOrderResponseDto;

import java.util.Arrays;
import java.util.List;

public class FindOrderResponseDtoFactory {

    public static FindOrderResponseDto createSuccessCase(){

        FindOrderOptionGroupResponseDto optionGroup_1 = new FindOrderOptionGroupResponseDto(
                "ram", "16GB"
        );

        FindOrderOptionGroupResponseDto optionGroup_2 = new FindOrderOptionGroupResponseDto(
                "ssd", "512GB"
        );

        FindOrderResponseDto result = FindOrderResponseDto
                .builder()
                .orderLineItemId(1L)
                .itemId(2L)
                .itemName("맥북")
                .itemImageUrl("www.google.com")
                .count(3)
                .orderStatus(OrderLineItemStatus.ORDER_ACCEPTED)
                .majorAddress("서울 중구 동일로 756")
                .detailAddress("999동 999호")
                .zipcode("02020")
                .shopId(7L)
                .shopName("멋쟁이들의 가게")
                .price(Money.from(10000))
                .build();
        result.setOrderOptionGroups(List.of(optionGroup_1, optionGroup_2));
        return result;
    }
}
