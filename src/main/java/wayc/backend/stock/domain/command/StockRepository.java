package wayc.backend.stock.domain.command;

import org.springframework.data.jpa.repository.JpaRepository;
import wayc.backend.stock.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
