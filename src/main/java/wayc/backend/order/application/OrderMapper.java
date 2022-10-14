package wayc.backend.order.application;

import org.springframework.stereotype.Component;
import wayc.backend.order.application.dto.request.CreateOrderLineItemRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderOptionGroupRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderOptionRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.OrderOption;
import wayc.backend.order.domain.OrderOptionGroup;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public Order mapFrom(CreateOrderRequestDto dto, Long memberId) {
        return new Order(
                memberId,
                dto.getShopId(),
                dto.getOrderLineItemsDto()
                        .stream()
                        .map(orderLineItemDto -> toOrderLineItem(orderLineItemDto))
                        .collect(Collectors.toList())
        );
    }

    private OrderLineItem toOrderLineItem(CreateOrderLineItemRequestDto dto){
        return new OrderLineItem(
                dto.getItemId(),
                dto.getName(),
                dto.getCount(),
                dto.getOrderOptionGroupsDto()
                        .stream()
                        .map(orderOptionGroupDto -> toOrderOptionGroup(orderOptionGroupDto))
                        .collect(Collectors.toList())
        );
    }

    private OrderOptionGroup toOrderOptionGroup(CreateOrderOptionGroupRequestDto dto) {
        return new OrderOptionGroup(
                dto.getName(),
                dto.getOrderOptionsDto()
                        .stream()
                        .map(orderOptionDto -> toOrderOption(orderOptionDto))
                        .collect(Collectors.toList())
        );
    }

    private OrderOption toOrderOption(CreateOrderOptionRequestDto dto) {
        return new OrderOption(dto.getName(), dto.getPrice());
    }
}
