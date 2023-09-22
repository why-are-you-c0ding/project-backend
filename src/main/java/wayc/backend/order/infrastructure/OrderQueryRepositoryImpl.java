package wayc.backend.order.infrastructure;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import wayc.backend.order.domain.repository.query.OrderQueryRepository;
import wayc.backend.order.domain.repository.query.dto.FindOrderOptionGroupResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindOrdersForCustomerResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindPagingOrderResponseDto;


import wayc.backend.utils.PagingUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static wayc.backend.order.domain.QOrder.*;
import static wayc.backend.order.domain.QOrderLineItem.*;
import static wayc.backend.order.domain.QOrderOptionGroup.*;
import static wayc.backend.shop.domain.QItem.*;
import static wayc.backend.shop.domain.QShop.*;

@Repository
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory query;

    public OrderQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }


    private final static Integer PAGE_SIZE = 10;

    @Override
    public FindPagingOrderResponseDto findCustomerOrdersWithPaging(Long memberId, Integer lastOrderLineItemId) {
        List<FindOrdersForCustomerResponseDto> result = query
                .from(order)
                .join(orderLineItem).on(orderLineItem.order.id.eq(order.id))
                .join(orderOptionGroup).on(orderOptionGroup.orderLineItem.id.eq(orderLineItem.id))
                .join(item).on(item.id.eq(orderLineItem.itemId))
                .join(shop).on(shop.id.eq(item.shop.id))
                .where(
                        order.orderer.memberId.eq(memberId),
                        orderLineItem.id.gt(lastOrderLineItemId)
                )
                .orderBy(orderLineItem.id.desc())
                .limit(PAGE_SIZE + 1)
                .transform(
                        groupBy(orderLineItem.id).list(
                                Projections.constructor(
                                        FindOrdersForCustomerResponseDto.class,
                                        item.imageUrl,
                                        shop.name,
                                        item.name,
                                        orderLineItem.count,
                                        shop.id,
                                        item.id,
                                        order.id,
                                        orderLineItem.orderLineItemStatus,
                                        orderLineItem.payment,
                                        list(
                                                Projections.constructor(
                                                        FindOrderOptionGroupResponseDto.class,
                                                        orderOptionGroup.name,
                                                        orderOptionGroup.orderOption.optionName
                                                )
                                        )
                                )
                        )
                );

        if(result.size() > PAGE_SIZE){
            return new FindPagingOrderResponseDto(false, PagingUtils.applyPaging(result));
        }
        return new FindPagingOrderResponseDto(true, result);
    }
}

/**
 *
 *
 * lt <
 *
 * loe <=
 *
 * gt >
 *
 * goe >=
 */