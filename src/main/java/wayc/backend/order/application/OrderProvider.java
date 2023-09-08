package wayc.backend.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import wayc.backend.order.application.dto.response.FindOrderResponseDto;
import wayc.backend.order.application.dto.response.FindOrdersForCustomerResponseDto;
import wayc.backend.order.application.dto.response.FindOrdersForSellerResponseDto;
import wayc.backend.order.application.dto.response.FindPagingOrderResponseDto;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.domain.repository.OrderDto;
import wayc.backend.order.domain.repository.PayRepository;
import wayc.backend.order.exception.NotExistsOrderException;
import wayc.backend.order.exception.NotExistsPayException;

import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.exception.NotExistsItemException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderProvider {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final PayRepository payRepository;

    public FindPagingOrderResponseDto findCustomerOrders(Long memberId, Integer page) {
        PageRequest paging = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Slice<OrderLineItem> pagingResult = orderRepository.findOrdersPagingByOrderingMemberId(memberId, paging);
        List<FindOrdersForCustomerResponseDto> result = pagingResult.stream()
                .map(order -> {
                    Item item = itemRepository
                            .findItemByItemId(order.getItemId())
                            .orElseThrow(NotExistsItemException::new);  //쿼리를 쪼개니까 문제가 발생함. item이 null일 때
                    Integer price = payRepository.findPayPriceByOrderId(order.getId())
                            .orElseThrow(NotExistsPayException::new);
                    return FindOrdersForCustomerResponseDto.of(order, item, price);
                })
                .collect(Collectors.toList());
        ;
        return new FindPagingOrderResponseDto(pagingResult.isLast(), result);
    }

    public FindOrderResponseDto findOrder(Long memberId, Long orderId) {
        OrderLineItem order = orderRepository.findOrderByOrderId(orderId)
                .orElseThrow(NotExistsOrderException::new);
        Item item = itemRepository.findItemByItemId(order.getItemId())
                .orElseThrow(NotExistsItemException::new);
        Integer price = payRepository.findPayPriceByOrderId(order.getId())
                .orElseThrow(NotExistsPayException::new);

        return FindOrderResponseDto.of(order, item, price);
    }

    public FindPagingOrderResponseDto findSellerOrders(Long memberId, Integer page) {
        PageRequest paging = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "created_at"));
        Page<OrderDto> pagingResult = orderRepository.findOrdersPagingByOwnerId(memberId, paging);
        List<FindOrdersForSellerResponseDto> result = pagingResult.stream()
                .map(dto -> FindOrdersForSellerResponseDto.of(dto))
                .collect(Collectors.toList());

        return new FindPagingOrderResponseDto(pagingResult.isLast(), result);
    }
}
