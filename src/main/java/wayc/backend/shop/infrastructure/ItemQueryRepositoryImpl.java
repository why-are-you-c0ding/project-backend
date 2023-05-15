package wayc.backend.shop.infrastructure;


import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.query.ItemQueryRepository;

import javax.persistence.EntityManager;

import java.util.List;

import static wayc.backend.shop.domain.QItem.*;
import static wayc.backend.shop.domain.QOptionGroup.optionGroup;
import static wayc.backend.shop.domain.QShop.shop;

@Repository
public class ItemQueryRepositoryImpl implements ItemQueryRepository {

    private final JPAQueryFactory query;

    public ItemQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Item> findItemBySearchKeyword(Integer page, String searchKeyword){

        NumberTemplate<Double> booleanTemplate = Expressions.numberTemplate(Double.class,
                "function('match2',{0},{1},{2})", item.name, item.category, "+" + searchKeyword + "*");
        return query.select(item)
                .distinct()
                .from(item)
                .where(
                        booleanTemplate.gt(0)
                )
                .join(item.shop, shop)
                .fetchJoin()
                .join(item.optionGroups, optionGroup)
                .fetchJoin()
                .offset(page)
                .limit(21)
                .fetch();
    }
}
