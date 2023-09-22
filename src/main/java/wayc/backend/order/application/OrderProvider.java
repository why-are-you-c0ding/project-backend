package wayc.backend.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import wayc.backend.order.domain.repository.query.dto.FindOrderResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindOrdersForCustomerResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindOrdersForSellerResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindPagingOrderResponseDto;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.repository.OrderLineItemRepository;
import wayc.backend.order.domain.repository.query.dto.OrderDto;
import wayc.backend.order.domain.repository.query.OrderQueryRepository;
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

    private final OrderQueryRepository orderQueryRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final ItemRepository itemRepository;
    private final PayRepository payRepository;

    public FindPagingOrderResponseDto findCustomerOrders(Long memberId, Integer lastOrderLineItemId) {
        return orderQueryRepository.findCustomerOrdersWithPaging(memberId, lastOrderLineItemId);
    }

    public FindOrderResponseDto findDetailOrderLineItem(Long memberId, Long orderId) {
        OrderLineItem order = orderLineItemRepository.findOrderLineItemById(orderId)
                .orElseThrow(NotExistsOrderException::new);
        Item item = itemRepository.findItemByItemId(order.getItemId())
                .orElseThrow(NotExistsItemException::new);
        Integer price = payRepository.findPayPriceByOrderId(order.getId())
                .orElseThrow(NotExistsPayException::new);

        return null;
    }

    public FindPagingOrderResponseDto findSellerOrders(Long memberId, Integer page) {
        PageRequest paging = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "created_at"));
        Page<OrderDto> pagingResult = orderLineItemRepository.findOrdersPagingByOwnerId(memberId, paging);
        List<FindOrdersForSellerResponseDto> result = pagingResult.stream()
                .map(dto -> FindOrdersForSellerResponseDto.of(dto))
                .collect(Collectors.toList());

        return new FindPagingOrderResponseDto(pagingResult.isLast(), result);
    }
}
