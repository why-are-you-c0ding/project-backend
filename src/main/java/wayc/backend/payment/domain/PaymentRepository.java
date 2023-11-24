package wayc.backend.payment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p.pay from Payment p where p.orderId = :orderId and p.status = 'ACTIVE' ")
    Optional<Integer> findPayPriceByOrderId(Long orderId);
}
