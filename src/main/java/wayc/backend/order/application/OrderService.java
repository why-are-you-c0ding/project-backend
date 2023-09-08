package wayc.backend.order.application;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.shop.domain.valid.ItemComparisonValidator;
import wayc.backend.shop.exception.NotExistsItemException;
import wayc.backend.shop.domain.command.ItemRepository;

import wayc.backend.order.exception.NotExistsOrderException;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.application.dto.request.UpdateOrderRequestDto;
import wayc.backend.order.domain.repository.OrderRepository;


import java.util.List;

@Transactional(readOnly = false)
@Service
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ItemComparisonValidator<OrderLineItem> itemComparisonValidator;

    public OrderService(ItemRepository itemRepository,
                        OrderRepository orderRepository,
                        OrderMapper orderMapper,
                        ItemComparisonValidator<OrderLineItem> itemComparator) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.itemComparisonValidator = itemComparator;
    }

    public void createOrder(Long memberId, List<CreateOrderRequestDto> dtoList) {
        List<OrderLineItem> orders = orderMapper.mapFrom(dtoList, memberId);
        validOrders(orders);
        orderRepository.saveAll(orders);
        orderCreated(orders);
    }

    private void validOrders(List<OrderLineItem> orders) {
        for (OrderLineItem order : orders) {
            order.place(itemComparisonValidator);
        }
    }

    private void orderCreated(List<OrderLineItem> orders) {
        for (OrderLineItem order : orders) {
            order.created();
        }
    }

    public void updateOrder(Long ownerId, UpdateOrderRequestDto dto) {

        //아이템의 주인이 맞는지 확인
        itemRepository.findItemByShopOwnerIdAndItemId(ownerId, dto.getItemId())
                .orElseThrow(NotExistsItemException::new);

        //아이템 아이디와 주문 번호로 찾아옴.
        OrderLineItem order = orderRepository.findOrderByOrderIdAndItemId(dto.getOrderId(), dto.getItemId())
                .orElseThrow(NotExistsOrderException::new);

        order.updateOrder(dto.getOrderStatus());
    }
}
