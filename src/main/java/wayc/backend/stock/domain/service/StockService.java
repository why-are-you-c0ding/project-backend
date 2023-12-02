package wayc.backend.stock.domain.service;

import java.util.List;

public interface StockService {
    void decreaseStock(Integer quantity, List<Long> optionIds);
}
