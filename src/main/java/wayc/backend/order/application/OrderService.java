package wayc.backend.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.dataaccess.OrderRepository;
import wayc.backend.order.domain.Order;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public void createOrder(CreateOrderRequestDto dto, Long memberId) {
        Order order = orderMapper.mapFrom(dto, memberId);
        //TODO  order vadliation을 해야함.
        orderRepository.save(order);
    }
}
