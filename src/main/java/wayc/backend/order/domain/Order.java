package wayc.backend.order.domain;

import lombok.*;

import wayc.backend.common.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "orders")
@ToString
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "orders_id")
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


    @Builder
    public Order(Long orderingMemberId, Long itemId, String name, Integer count, Address address, OrderStatus orderStatus, List<OrderOptionGroup> orderOptionGroups) {
        this.orderOptionGroups= orderOptionGroups;
        this.orderingMemberId = orderingMemberId;
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.address = address;
        this.orderStatus = orderStatus;
    }
}
