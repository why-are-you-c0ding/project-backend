package wayc.backend.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.application.dto.response.ShowOrderResponseDto;
import wayc.backend.order.dataaccess.OrderRepository;
import wayc.backend.order.domain.Order;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional(readOnly = false)
    public void createOrder(List<CreateOrderRequestDto> dto, Long memberId) {
        List<Order> order = orderMapper.mapFrom(dto, memberId);
        //TODO  order vadliation을 해야함.
        orderRepository.saveAll(order);
    }


    public List<ShowOrderResponseDto> showOrders(Long memberId) {
        return orderRepository.findOrdersByOrderingMemberId(memberId)
                .stream()
                .map(order -> ShowOrderResponseDto.of(order))
                .collect(Collectors.toList());
    }
}
