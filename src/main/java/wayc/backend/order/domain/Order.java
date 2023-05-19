package wayc.backend.order.domain;

import lombok.*;

import wayc.backend.common.domain.BaseEntity;
import wayc.backend.common.event.Events;
import wayc.backend.order.domain.event.DecreasedStockEvent;
import wayc.backend.order.domain.event.OrderPayedEvent;
import wayc.backend.order.domain.validator.OrderValidator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private Integer payment;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(Long id,
                 List<OrderOptionGroup> orderOptionGroups,
                 Long orderingMemberId, Long itemId,
                 String name, Integer count,
                 Address address,
                 OrderStatus orderStatus,
                 Integer payment
    ) {
        this.id = id;
        this.orderOptionGroups = orderOptionGroups;
        this.orderingMemberId = orderingMemberId;
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.address = address;
        this.orderStatus = orderStatus;
        this.payment = payment;
    }

    public void updateOrder(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void created() {
        Events.raise(new OrderPayedEvent(orderingMemberId, id, payment));
        Events.raise(new DecreasedStockEvent(count, extractOptionGroupIdList()));
    }

    private List<Long> extractOptionGroupIdList() {
        return orderOptionGroups.stream().map(OrderOptionGroup::getId).collect(Collectors.toList());
    }

    public void place(OrderValidator orderValidator){
        orderValidator.validate(this);
    }
}
