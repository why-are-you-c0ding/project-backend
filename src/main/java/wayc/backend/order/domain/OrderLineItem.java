package wayc.backend.order.domain;

import lombok.*;

import wayc.backend.common.domain.BaseEntity;
import wayc.backend.common.event.Events;
import wayc.backend.order.domain.event.TookOutStockEvent;
import wayc.backend.order.domain.event.OrderPayedEvent;
import wayc.backend.shop.domain.valid.ItemComparator;
import wayc.backend.shop.domain.valid.ItemComparisonValidator;
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

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderOptionGroup> orderOptionGroups = new ArrayList<>();

    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private Long itemId;

    private String name;

    private Integer count;

    private Integer payment;

    @Builder
    public OrderLineItem(Long id,
                         List<OrderOptionGroup> orderOptionGroups,
                         Long itemId,
                         String name,
                         Integer count,
                         Integer payment
    ) {
        this.id = id;
        this.orderOptionGroups = orderOptionGroups;
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.payment = payment;
    }

    public void updateOrder(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void created() {
        Events.raise(new OrderPayedEvent(orderer.getMemberId(), id, payment));
        Events.raise(new TookOutStockEvent(count, extractOptionGroupIdList()));
    }

    private List<Long> extractOptionGroupIdList() {
        return orderOptionGroups.stream().map(OrderOptionGroup::getId).collect(Collectors.toList());
    }

    public void place(ItemComparisonValidator<OrderLineItem> itemComparisonValidator){
        itemComparisonValidator.validate(this);
    }

    @Override
    public List<OptionGroupComparator> getComparisonOrderOptionGroups() {
        return orderOptionGroups
                .stream()
                .map(orderOptionGroup -> (OptionGroupComparator) orderOptionGroup)
                .collect(Collectors.toList());
    }
}
