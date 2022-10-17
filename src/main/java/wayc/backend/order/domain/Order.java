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
    private List<OrderOptionGroup> items = new ArrayList<>();

    private Long orderingMemberId;

    private Long itemId;

    private String name;

    private Integer count;

    public Order(Long orderingMemberId, Long itemId, String name, Integer count, List<OrderOptionGroup> items) {
        this.items = items;
        this.orderingMemberId = orderingMemberId;
        this.itemId = itemId;
        this.name = name;
        this.count = count;
    }
}
