package wayc.backend.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.common.event.Events;
import wayc.backend.order.domain.event.OrderPayedEvent;
import wayc.backend.order.domain.event.TookOutStockEvent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Embedded
    private Address address;

    @Embedded
    private Orderer orderer;

    private Integer totalPayment;

    public Order(List<OrderLineItem> orderLineItems, Address address, Orderer orderer, Integer totalPayment) {
        this.orderLineItems = orderLineItems;
        this.address = address;
        this.orderer = orderer;
        this.totalPayment = totalPayment;
    }

    public void created() {
        Events.raise(new OrderPayedEvent(orderer.getMemberId(), id, totalPayment));
    }
}
