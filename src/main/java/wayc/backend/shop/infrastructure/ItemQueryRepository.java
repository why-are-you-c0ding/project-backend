package wayc.backend.shop.infrastructure;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.QOptionGroupSpecification;

import javax.persistence.EntityManager;

import java.util.List;

import static wayc.backend.shop.domain.QItem.*;
import static wayc.backend.shop.domain.QOptionGroupSpecification.*;
import static wayc.backend.shop.domain.QShop.shop;

@Repository
public class ItemQueryRepository {

    private final JPAQueryFactory query;

    public ItemQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Item> findRecommendedItem(List<String> names ){

        StringBuffer sb= new StringBuffer();

        names.stream()
                .forEach(str -> sb.append(str));
        System.out.println("sb.toString() = " + sb.toString());

        System.out.println(sb.toString().equals("health"));
        NumberTemplate booleanTemplate= Expressions.numberTemplate(Double.class,
                "function('match',{0},{1})", item.name, "+" + "health" + "*");


        return query.select(item)
                .from(item)
                .where(
                        booleanTemplate.gt(0)
                )
                .join(item.shop, shop)
                .fetchJoin()
                .join(item.optionGroupSpecifications, optionGroupSpecification)
                .fetchJoin()
                .limit(20)
                .fetch();
    }
}
