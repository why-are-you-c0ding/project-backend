package wayc.backend.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wayc.backend.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
