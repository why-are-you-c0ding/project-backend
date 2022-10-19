package wayc.backend.order.infrastructure;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wayc.backend.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.orderingMemberId =:memberId and o.status = 'ACTIVE'")
    Slice<Order> findOrdersPagingByOrderingMemberId(Long memberId, Pageable pageable);


//    @Query("select o from Order o where o. =:memberId and o.status = 'ACTIVE'")
//    List<Order> findOrdersByOrShopIdId(Long memberId);
}
