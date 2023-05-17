package wayc.backend.order.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.shop.exception.NotExistsItemException;

import wayc.backend.order.exception.NotExistsOrderException;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.application.dto.request.UpdateOrderRequestDto;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.domain.Order;

import wayc.backend.shop.domain.command.ItemRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional(readOnly = false)
    public void createOrder(Long memberId, List<CreateOrderRequestDto> dtoList) {
        List<Order> orders = orderMapper.mapFrom(dtoList, memberId);

        //TODO  order vadliation을 해야함.
        orderRepository.saveAll(orders);
        orderCreated(orders);
    }

    private void orderCreated(List<Order> orders) {
        for (Order order : orders) {
            order.created();
        }
    }

    @Transactional(readOnly = false)
    public void updateOrder(Long ownerId, UpdateOrderRequestDto dto) {

        //아이템의 주인이 맞는지 확인
        itemRepository.findItemByShopOwnerIdAndItemId(ownerId, dto.getItemId())
                .orElseThrow(NotExistsItemException::new);

        //아이템 아이디와 주문 번호로 찾아옴.
        Order order = orderRepository.findOrderByOrderIdAndItemId(dto.getOrderId(), dto.getItemId())
                .orElseThrow(NotExistsOrderException::new);

        order.updateOrder(dto.getOrderStatus());
    }
}
