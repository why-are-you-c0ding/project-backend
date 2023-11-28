package wayc.backend.order.domain;

import lombok.*;

import org.springframework.security.core.parameters.P;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.shop.domain.valid.ItemComparator;
import wayc.backend.shop.domain.valid.OptionGroupComparator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLineItem extends BaseEntity implements ItemComparator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "orderLineItem", cascade = CascadeType.PERSIST)
    private List<OrderOptionGroup> orderOptionGroups = new ArrayList<>();

    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private Long itemId;

    private String name;

    private Integer count;

    private Integer payment;

    @Enumerated(EnumType.STRING)
    private OrderLineItemStatus orderLineItemStatus;

    @Builder
    public OrderLineItem(Long id,
                         Long itemId,
                         String name,
                         Integer count,
                         Integer payment,
                         OrderLineItemStatus orderLineItemStatus,
                         List<OrderOptionGroup> orderOptionGroups
    ) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.payment = payment;
        this.orderLineItemStatus = orderLineItemStatus;
        this.orderOptionGroups = orderOptionGroups;
        orderOptionGroups.forEach(orderOptionGroup -> orderOptionGroup.mapOrderLineItem(this));
    }


    public void updateOrder(OrderLineItemStatus orderLineItemStatus) {
        this.orderLineItemStatus = orderLineItemStatus;
    }

    @Override
    public List<OptionGroupComparator> getComparisonOrderOptionGroups() {
        return orderOptionGroups
                .stream()
                .map(orderOptionGroup -> (OptionGroupComparator) orderOptionGroup)
                .collect(Collectors.toList());
    }

    protected void mapOrder(Order order) {
        this.order = order;
    }

    public void refund() {
        this.orderLineItemStatus = OrderLineItemStatus.REFUND;
    }
}
