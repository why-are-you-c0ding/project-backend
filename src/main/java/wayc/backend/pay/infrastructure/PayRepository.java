package wayc.backend.pay.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wayc.backend.pay.domain.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {
}
