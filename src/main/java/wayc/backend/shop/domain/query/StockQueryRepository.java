package wayc.backend.shop.domain.query;

import wayc.backend.shop.domain.OptionSpecification;
import wayc.backend.shop.domain.Stock;

import java.util.List;

public interface StockQueryRepository {
    Stock findStocks(List<OptionSpecification> options);
}
// TODO 추후에 DTO로 뽑자 엔티티가 아니라
