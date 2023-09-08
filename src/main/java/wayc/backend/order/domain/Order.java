package wayc.backend.order.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Embedded
    private Orderer orderer;


    public Order(Address address, OrderStatus orderStatus, Orderer orderer) {
        this.orderLineItems = orderLineItems;
        this.address = address;
        this.orderStatus = orderStatus;
        this.orderer = orderer;
    }
}
