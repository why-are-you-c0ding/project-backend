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
    private List<OrderOptionGroup> orderOptionGroups = new ArrayList<>();

    private Long orderingMemberId;

    private Long itemId;

    private String name;

    private Integer count;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(Long orderingMemberId, Long itemId, String name, Integer count, List<OrderOptionGroup> orderOptionGroups) {
        this.orderOptionGroups = orderOptionGroups;
        this.orderingMemberId = orderingMemberId;
        this.itemId = itemId;
        this.name = name;
        this.count = count;
    }

    public Order(Long id, List<OrderOptionGroup> orderOptionGroups, Long orderingMemberId, Long itemId, String name, Integer count, Address address, OrderStatus orderStatus) {
        this.id = id;
        this.orderOptionGroups= orderOptionGroups;
        this.orderingMemberId = orderingMemberId;
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.address = address;
        this.orderStatus = orderStatus;
    }
}
