package wayc.backend.order.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.domain.validator.OrderValidator;
import wayc.backend.shop.exception.NotExistsItemException;
import wayc.backend.shop.domain.command.ItemRepository;

import wayc.backend.order.exception.NotExistsOrderException;
import wayc.backend.order.application.dto.request.UpdateOrderRequestDto;
import wayc.backend.order.domain.repository.OrderLineItemRepository;


import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final OrderMapper orderMapper;
    private final OrderValidator orderValidator;


    public Long createOrder(CreateOrderRequestDto dto) {
        Order order = orderMapper.mapFrom(dto);
        orderValidator.validate(order);
        orderRepository.save(order);
        order.created();
        return order.getId();
    }


    public void updateOrder(Long ownerId, UpdateOrderRequestDto dto) {

        //아이템의 주인이 맞는지 확인
        itemRepository.findItemByShopOwnerIdAndItemId(ownerId, dto.getItemId())
                .orElseThrow(NotExistsItemException::new);

        //아이템 아이디와 주문 번호로 찾아옴.
        OrderLineItem order = orderLineItemRepository.findOrderLineItemByIdAndItemId(dto.getOrderId(), dto.getItemId())
                .orElseThrow(NotExistsOrderException::new);

        //TODO 별동
        order.updateOrder(dto.getOrderStatus());
    }
}
