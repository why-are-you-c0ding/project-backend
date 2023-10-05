package wayc.backend.order.infrastructure;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import wayc.backend.order.domain.repository.query.OrderQueryRepository;
import wayc.backend.order.domain.repository.query.dto.*;


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


    private final static Integer LIMIT_SIZE = 30;
    private final static Integer ADDITIONAL_LIMIT_SIZE = 3; //옵션 그룹은 최대 3개까지 가능.
    private final static Integer PAGE_SIZE = 10;


    @Override
    public FindPagingOrderResponseDto findCustomerOrderLineItemsWithPaging(Long memberId, Long lastOrderLineItemId) {
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
                .limit(LIMIT_SIZE + ADDITIONAL_LIMIT_SIZE)
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
                                        orderLineItem.id,
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

    @Override
    public FindPagingOrderResponseDto findSellerOrderLineItemsWithPaging(Long memberId, Long lastOrderLineItemId) {
        List<FindOrdersForSellerResponseDto> result = query
                .from(order)
                .join(orderLineItem).on(orderLineItem.order.id.eq(order.id))
                .join(orderOptionGroup).on(orderOptionGroup.orderLineItem.id.eq(orderLineItem.id))
                .join(item).on(item.id.eq(orderLineItem.itemId))
                .join(shop).on(shop.id.eq(item.shop.id))
                .where(
                        shop.owner.memberId.eq(memberId),
                        orderLineItem.id.gt(lastOrderLineItemId)
                )
                .orderBy(orderLineItem.id.desc())
                .limit(LIMIT_SIZE + ADDITIONAL_LIMIT_SIZE)
                .transform(
                        groupBy(orderLineItem.id).list(
                                Projections.constructor(
                                        FindOrdersForSellerResponseDto.class,
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

    @Override
    public FindOrderResponseDto findDetailOrderLineItem(Long orderLineItemId) {
        FindOrderResponseDto orderLineItem = findOrderLineItem(orderLineItemId);
        List<FindOrderOptionGroupResponseDto> optionGroups = findOrderOptionGroup(orderLineItemId);
        orderLineItem.setOrderOptionGroups(optionGroups);
        return orderLineItem;

    }

    private FindOrderResponseDto findOrderLineItem(Long orderLineItemId) {
        return query
                .select(
                        Projections.constructor(
                                FindOrderResponseDto.class,
                                orderLineItem.id,
                                item.id,
                                item.name,
                                item.imageUrl,
                                orderLineItem.count,
                                orderLineItem.payment,
                                orderLineItem.orderLineItemStatus,
                                order.address.major,
                                order.address.detail,
                                order.address.zipcode,
                                shop.name,
                                shop.id
                        )
                )
                .from(orderLineItem)
                .join(order).on(order.id.eq(orderLineItem.order.id))
                .join(item).on(orderLineItem.itemId.eq(item.id))
                .join(shop).on(shop.id.eq(item.shop.id))
                .where(orderLineItem.id.eq(orderLineItemId))
                .fetchFirst();
    }

    private List<FindOrderOptionGroupResponseDto> findOrderOptionGroup(Long orderLineItemId) {
        return query.select(
                Projections.constructor(
                        FindOrderOptionGroupResponseDto.class,
                        orderOptionGroup.name,
                        orderOptionGroup.orderOption.optionName
                ))
                .from(orderOptionGroup)
                .join(orderLineItem).on(orderOptionGroup.orderLineItem.id.eq(orderOptionGroup.orderLineItem.id))
                .where(orderLineItem.id.eq(orderLineItemId))
                .fetch();
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