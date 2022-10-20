package wayc.backend.order.application;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.exception.order.NotExistsOrderException;
import wayc.backend.exception.shop.NotExistsItemException;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.application.dto.request.UpdateOrderRequestDto;
import wayc.backend.order.application.dto.response.ShowOrderResponseDto;
import wayc.backend.order.application.dto.response.ShowOrdersForCustomerResponseDto;
import wayc.backend.order.application.dto.response.ShowOrdersForSellerResponseDto;
import wayc.backend.order.application.dto.response.ShowTotalOrderResponseDto;
import wayc.backend.order.infrastructure.OrderDto;
import wayc.backend.order.infrastructure.OrderRepository;
import wayc.backend.order.domain.Order;

import wayc.backend.shop.infrastructure.ItemRepository;
import wayc.backend.shop.domain.Item;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Transactional(readOnly = false)
    public List<Long> createOrder(List<CreateOrderRequestDto> dto, Long memberId) {
        List<Order> orders = orderMapper.mapFrom(dto, memberId);
        //TODO  order vadliation을 해야함.
        orderRepository.saveAll(orders);
        return orders.stream()
                .map(order -> order.getId())
                .collect(Collectors.toList());
    }

    public ShowTotalOrderResponseDto showCustomerOrders(Long memberId, Integer page) {
        PageRequest paging = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Slice<Order> pagingResult = orderRepository.findOrdersPagingByOrderingMemberId(memberId, paging);
        List<ShowOrdersForCustomerResponseDto> result = pagingResult.stream()
                .map(order -> {
                    Item item = itemRepository
                            .findItemByItemId(order.getItemId())
                            .orElseThrow(NotExistsItemException::new);  //쿼리를 쪼개니까 문제가 발생함. item이 null일 때
                    return ShowOrdersForCustomerResponseDto.of(order, item);
                })
                .collect(Collectors.toList());
        ;
        return new ShowTotalOrderResponseDto(pagingResult.isLast(), result);
    }

    public ShowTotalOrderResponseDto showSellerOrders(Long memberId, Integer page) {
        PageRequest paging = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "created_at"));
        Page<OrderDto> pagingResult = orderRepository.findOrdersPagingByOwnerId(memberId, paging);

        List<ShowOrdersForSellerResponseDto> result = pagingResult.stream()
                .map(dto ->
                        new ShowOrdersForSellerResponseDto(
                                dto.getItemImageUrl(),
                                dto.getOrderId(),
                                dto.getCount(),
                                dto.getItemName(),
                                dto.getCreatedAt(),
                                dto.getItemId(),
                                dto.getOrderStatus()
                ))
                .collect(Collectors.toList());

        return new ShowTotalOrderResponseDto(pagingResult.isLast(), result);
    }

    public ShowOrderResponseDto showOrder(Long memberId, Long orderId) {
        Order order = orderRepository.findOrderByOrderIdAndOrderingMemberId(orderId, memberId)
                .orElseThrow(NotExistsOrderException::new);
        Item item = itemRepository.findItemByItemId(order.getItemId())
                .orElseThrow(NotExistsItemException::new);

        return ShowOrderResponseDto.of(order, item);
    }

    @Transactional(readOnly = false)
    public void updateOrder(Long ownerId, UpdateOrderRequestDto dto) {

        //아이템의 주인이 맞는지 확인
        itemRepository.findItemByShopOwnerIdAndItemId(ownerId, dto.getItemId())
                .orElseThrow();

        //아이템 아이디와 주문 번호로 찾아옴.
        Order order = orderRepository.findOrderByOrderIdAndItemId(dto.getOrderId(), dto.getItemId())
                .orElseThrow();

        order.updateOrder(dto.getOrderStatus());
    }
}
