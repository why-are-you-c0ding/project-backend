package wayc.backend.shop.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import wayc.backend.shop.domain.*;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.query.StockQueryRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static wayc.backend.shop.domain.QStockOption.stockOption;


@Repository
public class StockQueryRepositoryImpl implements StockQueryRepository {

    private final JPAQueryFactory query;

    public StockQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public Stock findStocks(List<Option> options) {
        return query
                .select(stockOption.stock)
                .from(stockOption)
                .where(
                        stockOption.option.in(options)
                )
                .groupBy(stockOption.stock.id)
                .orderBy(stockOption.stock.id.count().desc())
                .limit(1)
                .fetchFirst();

    }
}

