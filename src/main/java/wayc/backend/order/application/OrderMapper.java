package wayc.backend.order.application;

import org.springframework.stereotype.Component;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderOptionGroupRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderOptionRequestDto;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderOption;
import wayc.backend.order.domain.OrderOptionGroup;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public List<Order> mapFrom(List<CreateOrderRequestDto> dto, Long memberId) {
        return dto.stream()
                .map(orderDto ->
                        new Order(
                                orderDto.getItemId(),
                                memberId,
                                orderDto.getName(),
                                orderDto.getCount(),
                                orderDto.getOrderOptionGroupsDto()
                                        .stream()
                                        .map(orderOptionGroupDto -> toOrderOptionGroup(orderOptionGroupDto))
                                        .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
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
