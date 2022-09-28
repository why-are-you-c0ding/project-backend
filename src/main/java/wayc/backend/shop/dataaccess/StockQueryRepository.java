package wayc.backend.shop.dataaccess;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import wayc.backend.common.base.BaseStatus;
import wayc.backend.shop.domain.*;

import javax.persistence.EntityManager;
import java.util.List;

import static wayc.backend.shop.domain.QOptionSpecification.*;
import static wayc.backend.shop.domain.QStock.stock;
import static wayc.backend.shop.domain.QStockOptionSpecification.*;


@Repository
public class StockQueryRepository {

    private final JPAQueryFactory query;

    public StockQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }


    public Stock findStocks(List<OptionSpecification> options) {
        return query
                .select(stockOptionSpecification.stock)
                .from(stockOptionSpecification)
                .where(
                        stockOptionSpecification.optionSpecification.in(options)
                )
                .groupBy(stockOptionSpecification.stock.id)
                .orderBy(stockOptionSpecification.stock.id.count().desc())
                .limit(1)
                .fetchFirst();

    }
}

