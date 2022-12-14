package wayc.backend.shop.domain.command;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wayc.backend.shop.domain.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
