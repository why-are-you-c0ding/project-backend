package wayc.backend.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import wayc.backend.order.domain.OrderLineItem;

import java.util.Optional;

@Repository
public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {

    @Query("select olt from OrderLineItem olt where olt.id = :orderLineItemId and olt.itemId = :itemId and olt.status = 'ACTIVE'")
    Optional<OrderLineItem> findOrderLineItemByIdAndItemId(Long orderLineItemId, Long itemId);

    @Query("select olt from OrderLineItem olt where olt.id = :orderLineItemId and olt.status = 'ACTIVE'")
    Optional<OrderLineItem> findByIdAndStatus(Long orderLineItemId);
}

