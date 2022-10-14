package wayc.backend.order.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wayc.backend.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
