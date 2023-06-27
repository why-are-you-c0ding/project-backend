package wayc.backend.stock.domain.query;

import wayc.backend.stock.domain.Stock;

import java.util.List;

public interface StockQueryRepository {
    Stock findStock(List<Long> optionIdList);
    Integer findStocks(List<Long> optionIdList);
}