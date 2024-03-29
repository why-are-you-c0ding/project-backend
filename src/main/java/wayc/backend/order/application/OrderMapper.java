package wayc.backend.order.application;

import org.springframework.stereotype.Component;
import wayc.backend.common.domain.Money;
import wayc.backend.order.application.dto.request.CreateOrderLineItemRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderOptionGroupRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderOptionRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.domain.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OrderMapper {

    public Order mapFrom(CreateOrderRequestDto dto) {
        return new Order(
                toOrderLineItem(dto),
                dto.toAddress(),
                new Orderer(dto.getOrdererId()),
                dto.getTotalPayment()
        );
    }

    private List<OrderLineItem> toOrderLineItem(CreateOrderRequestDto dto) {
        return dto
                .getOrderLineItems()
                .stream()
                .map(orderLineItem ->
                        OrderLineItem.builder()
                                .itemId(orderLineItem.getItemId())
                                .name(orderLineItem.getName())
                                .count(orderLineItem.getCount())
                                .price(orderLineItem.getPayment())
                                .orderOptionGroups(
                                        orderLineItem.getOrderOptionGroups()
                                                .stream()
                                                .map(orderOptionGroupDto -> toOrderOptionGroup(orderOptionGroupDto))
                                                .collect(Collectors.toList()))
                                .orderLineItemStatus(OrderLineItemStatus.ORDER_ACCEPTED)
                                .build()
                )
                .collect(Collectors.toList());
    }


    private OrderOptionGroup toOrderOptionGroup(CreateOrderOptionGroupRequestDto dto) {
        return new OrderOptionGroup(toOrderOption(dto.getOrderOptionsDto()), dto.getName());
    }

    private OrderOption toOrderOption(CreateOrderOptionRequestDto dto) {
        return new OrderOption(dto.getName(), Money.from(dto.getPrice()));
    }
}
