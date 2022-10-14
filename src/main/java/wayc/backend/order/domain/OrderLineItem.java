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
public class OrderLineItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JoinColumn(name = "order_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Order order;

    private Long itemId;

    private String name;

    private int count;

    @JoinColumn
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderOptionGroup> orderOptionGroups = new ArrayList<>();

    public OrderLineItem(Long itemId, String name, int count, List<OrderOptionGroup> orderOptionGroups) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.orderOptionGroups.addAll(orderOptionGroups);
    }
}
