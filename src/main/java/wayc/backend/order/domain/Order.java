package wayc.backend.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItem> items = new ArrayList<>();

    private Long orderingMemberId;

    private Long shopId;

    public Order(Long orderingMemberId, Long shopId) {
        this.orderingMemberId = orderingMemberId;
        this.shopId = shopId;
    }

    public Order(Long orderingMemberId, Long shopId, List<OrderLineItem> items) {
        this.orderingMemberId = orderingMemberId;
        this.shopId = shopId;
        this.items.addAll(items);
    }
}
