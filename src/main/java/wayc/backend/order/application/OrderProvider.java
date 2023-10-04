package wayc.backend.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import wayc.backend.order.domain.repository.query.dto.FindOrderResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindPagingOrderResponseDto;
import wayc.backend.order.domain.repository.query.OrderQueryRepository;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderProvider {

    private final OrderQueryRepository orderQueryRepository;

    public FindPagingOrderResponseDto findCustomerOrderLineItems(Long memberId, Integer lastOrderLineItemId) {
        return orderQueryRepository.findCustomerOrderLineItemsWithPaging(memberId, lastOrderLineItemId);
    }

    public FindOrderResponseDto findDetailOrderLineItem(Long orderLineItemId) {
        return orderQueryRepository.findDetailOrderLineItem(orderLineItemId);
    }

    public FindPagingOrderResponseDto findSellerOrderLineItems(Long memberId, Integer lastOrderLineItemId) {
        return orderQueryRepository.findSellerOrderLineItemsWithPaging(memberId, lastOrderLineItemId);
    }
}
