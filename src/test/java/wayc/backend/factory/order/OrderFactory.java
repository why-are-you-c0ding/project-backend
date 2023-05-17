package wayc.backend.factory.order;

import wayc.backend.order.application.OrderMapper;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OrderFactory {

    private OrderFactory(){}

    public static Order create(Long itemId){
        return Order.builder()
                .itemId(itemId)
                .orderStatus(OrderStatus.ONGOING)
                .count(3)
                .name("맥북")
                .payment(6000)
                .orderingMemberId(1L)
                .address(new Address("서울", "어딘가 디테일한 주소", "00000"))
                .orderOptionGroups(List.of(
                        new OrderOptionGroup(new OrderOption("16GB", 80000), "RAM"),
                        new OrderOptionGroup(new OrderOption("512GB", 80000), "SSD")
                ))
                .build();
    }

    public static List<CreateOrderRequestDto> createServiceDto(){
        return CreateOrderRequestFactory.createSuccessCase()
                .stream()
                .map(dto -> dto.toServiceDto())
                .collect(Collectors.toList());
    }
}
