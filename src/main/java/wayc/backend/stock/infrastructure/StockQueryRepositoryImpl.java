package wayc.backend.stock.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

import wayc.backend.stock.domain.query.StockQueryRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static wayc.backend.stock.domain.QStock.*;
import static wayc.backend.stock.domain.QStockOption.stockOption;


@Repository
public class StockQueryRepositoryImpl implements StockQueryRepository {

    private final JPAQueryFactory query;

    public StockQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public Integer findStocks(List<Long> options) {
        return query
                .select(stock.quantity)
                .from(stockOption)
                .join(stock).on(stockOption.stockId.eq(stock.id))
                .where(
                        stockOption.optionId.in(options)
                )
                .groupBy(stockOption.stockId)
                .orderBy(stockOption.stockId.count().desc())
                .limit(1)
                .fetchFirst();
    }
}

