package wayc.backend.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.common.event.Events;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static wayc.backend.order.domain.OrderLineItemStatus.*;

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

    /**
     * 주문은 불변
     */
    public Order(List<OrderLineItem> orderLineItems, Address address, Orderer orderer, Integer totalPayment) {
        this.orderLineItems = orderLineItems;
        this.address = address;
        this.orderer = orderer;
        this.totalPayment = totalPayment;
        orderLineItems.forEach(orderLineItem -> orderLineItem.mapOrder(this));
    }

    public void created() {
        orderLineItems
                .forEach(orderLineItem -> orderLineItem.updateOrder(ORDER_ACCEPTED));
    }

    public void readyPayment() {
        orderLineItems
                .forEach(orderLineItem -> orderLineItem.updateOrder(READY_FOR_PAYMENT));
    }

    public void completePayment() {
        orderLineItems
                .forEach(orderLineItem -> orderLineItem.updateOrder(PAYMENT_COMPLETED));
    }

    public void cancelPayment() {
        orderLineItems
                .forEach(orderLineItem -> orderLineItem.updateOrder(PAYMENT_CANCELED));
    }
}
