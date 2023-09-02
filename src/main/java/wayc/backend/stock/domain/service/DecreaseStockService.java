package wayc.backend.stock.domain.service;

import java.util.List;

public interface DecreaseStockService {
    void decreaseStock(Integer quantity, List<Long> optionIds);
}