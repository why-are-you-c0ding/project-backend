package wayc.backend.order.domain.repository.query;

import wayc.backend.order.domain.repository.query.dto.FindPagingOrderResponseDto;

public interface OrderQueryRepository {
    FindPagingOrderResponseDto findCustomerOrdersWithPaging(Long memberId, Integer lastOrderLineItemId);
}
