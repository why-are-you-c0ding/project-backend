package wayc.backend.order.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.repository.query.dto.OrderDto;

import java.util.Optional;

@Repository
public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {

    @Query(nativeQuery = true,
            countQuery = "select count(*) from orders",
            value =
            "select i.id as itemId, i.image_url as itemImageUrl, i.name as itemName, o.count, " +
                    " o.id as orderId, o.created_at as createdAt, o.order_status as orderStatus, pay.pay as price from orders as o " +
                    " join( select i.id, i.image_url, i.name, i.status  from item i where i.shop_id = " +
                    "                                   (select shop.id from shop where shop.member_id =:ownerId) ) " +
                    "    as i on i.id = o.item_id and i.status = 'ACTIVE' " +
                    " join pay on pay.order_id = o.id and pay.status = 'ACTIVE' " +
                    " where o.status = 'ACTIVE'")
    Page<OrderDto> findOrdersPagingByOwnerId(Long ownerId, Pageable page);

//    일대다 컬렉션 페치 조인을 하게 되면 데이터 뻥튀기가 발생하는데 이를 막기 위해 distinct 사용
//    @Query("select distinct o from OrderLineItem o join fetch o.orderOptionGroups where o.id = :orderId and o.status = 'ACTIVE'")
//    Optional<OrderLineItem> findOrderLineItemById(Long orderId);

    @Query("select olt from OrderLineItem olt where olt.id = :orderLineItemId and olt.itemId = :itemId and olt.status = 'ACTIVE'")
    Optional<OrderLineItem> findOrderLineItemByIdAndItemId(Long orderLineItemId, Long itemId);
}

