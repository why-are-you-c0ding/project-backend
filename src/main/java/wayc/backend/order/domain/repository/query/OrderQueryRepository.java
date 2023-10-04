package wayc.backend.order.domain.repository.query;

import wayc.backend.order.domain.repository.query.dto.FindOrderResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindPagingOrderResponseDto;

public interface OrderQueryRepository {
    FindPagingOrderResponseDto findCustomerOrderLineItemsWithPaging(Long memberId, Integer lastOrderLineItemId);
    FindPagingOrderResponseDto findSellerOrderLineItemsWithPaging(Long memberId, Integer lastOrderLineItemId);
    FindOrderResponseDto findDetailOrderLineItem(Long orderId);
}
