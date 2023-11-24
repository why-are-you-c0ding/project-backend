package wayc.backend.pay.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {

    @Query("select p.pay from Pay p where p.orderId = :orderId and p.status = 'ACTIVE' ")
    Optional<Integer> findPayPriceByOrderId(Long orderId);
}
