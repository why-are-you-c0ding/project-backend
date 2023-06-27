package wayc.backend.stock.domain.command;

import org.springframework.data.jpa.repository.JpaRepository;
import wayc.backend.stock.domain.StockOption;

public interface StockOptionRepository extends JpaRepository<StockOption, Long> {
}
