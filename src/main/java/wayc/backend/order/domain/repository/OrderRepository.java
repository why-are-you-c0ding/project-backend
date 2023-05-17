package wayc.backend.order.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import wayc.backend.order.domain.Order;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.orderingMemberId =:memberId and o.status = 'ACTIVE'")
    Slice<Order> findOrdersPagingByOrderingMemberId(Long memberId, Pageable pageable);

    @Query(nativeQuery = true,
            countQuery = "select count(*) from orders",
            value =
            "select i.id as itemId, i.image_url as itemImageUrl, i.name as itemName, o.count, " +
                    " o.id as orderId, o.created_at as createdAt, o.order_status as orderStatus, pay.pay as price from orders as o " +
                    " join( select i.id, i.image_url, i.name, i.status  from item i where i.shop_id = " +
                    "                                   (select shop.id from shop where shop.owner_id =:ownerId) ) " +
                    "    as i on i.id = o.item_id and i.status = 'ACTIVE' " +
                    " join pay on pay.order_id = o.id and pay.status = 'ACTIVE' " +
                    " where o.status = 'ACTIVE'")
    Page<OrderDto> findOrdersPagingByOwnerId(Long ownerId, Pageable page);

    //일대다 컬렉션 페치 조인을 하게 되면 데이터 뻥튀기가 발생하는데 이를 막기 위해 distinct 사용
    @Query("select distinct o from Order o join fetch o.orderOptionGroups where o.id = :orderId and o.status = 'ACTIVE'")
    Optional<Order> findOrderByOrderId(Long orderId);

    @Query("select o from Order o  where o.id = :orderId and o.orderingMemberId = :memberId and o.orderStatus = 'BEFORE_PAY' and o.status = 'ACTIVE'")
    Optional<Order> findOrderByOrderIdAndOrderingMemberIdAndOrderStatus(Long orderId, Long memberId);

    @Query("select o from Order o where o.id = :orderId and o.itemId = :itemId and o.status = 'ACTIVE'")
    Optional<Order> findOrderByOrderIdAndItemId(Long orderId, Long itemId);
}



/**
 * select * from orders as o
 * join( select i.id from item i where i.shop_id =
 *                                    (select shop.id from shop where shop.owner_id =:ownerId) )
 *     as i on i.id = o.item_id
 * where o.status = 'ACTIVE' order by o.created_at desc limit :page, 10
 *
 *
 * https://www.baeldung.com/spring-data-jpa-query
 */

