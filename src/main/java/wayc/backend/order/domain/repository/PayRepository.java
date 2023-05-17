package wayc.backend.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import wayc.backend.order.domain.pay.Pay;

import java.util.Optional;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {

    @Query("select p.price from Pay p where p.orderId = :orderId and p.status = 'ACTIVE' ")
    Optional<Integer> findPayPriceByOrderId(Long orderId);
}
