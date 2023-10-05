package wayc.backend.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wayc.backend.common.domain.BaseStatus;
import wayc.backend.order.domain.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.id = :id and o.status = 'ACTIVE'")
    Optional<Order> findOrderById(Long id);
}
