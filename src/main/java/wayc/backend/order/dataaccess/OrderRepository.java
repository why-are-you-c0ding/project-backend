package wayc.backend.order.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wayc.backend.order.domain.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.orderingMemberId =:memberId and o.status = 'ACTIVE'")
    List<Order> findOrdersByOrderingMemberId(Long memberId);
}
