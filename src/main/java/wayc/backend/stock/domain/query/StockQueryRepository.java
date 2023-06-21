package wayc.backend.stock.domain.query;

import java.util.List;

public interface StockQueryRepository {
    Integer findStocks(List<Long> optionIdList);
}
// TODO 추후에 DTO로 뽑자 엔티티가 아니라
