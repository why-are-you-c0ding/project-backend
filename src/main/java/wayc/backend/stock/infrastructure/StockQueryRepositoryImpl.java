package wayc.backend.stock.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

import wayc.backend.stock.domain.Stock;
import wayc.backend.stock.domain.query.StockQueryRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static wayc.backend.stock.domain.QStock.stock;
import static wayc.backend.stock.domain.QStockOption.stockOption;

@Repository
public class StockQueryRepositoryImpl implements StockQueryRepository {

    private final JPAQueryFactory query;

    public StockQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Stock findStock(List<Long> optionIds) {
        return query
                .select(stock)
                .from(stockOption)
                .join(stock)
                .on(stockOption.stockId.eq(stock.id))
                .where(
                        stockOption.optionId.in(optionIds)
                )
                .groupBy(stockOption.stockId)
                .fetchFirst();
    }

    @Override
    public Integer findStocks(List<Long> options) {
        return query
                .select(stock.quantity)
                .from(stockOption)
                .join(stock)
                .on(stockOption.stockId.eq(stock.id))
                .where(
                        stockOption.optionId.in(options)
                )
                .groupBy(stockOption.stockId)
                .fetchFirst();
    }
}

