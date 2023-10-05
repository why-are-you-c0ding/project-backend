package wayc.backend.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.shop.domain.valid.OptionGroupComparator;
import wayc.backend.shop.domain.valid.OptionGroupValidator;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OrderOptionGroup extends BaseEntity implements OptionGroupComparator {

    @Id
    @Column(name = "order_option_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderLineItem orderLineItem;

    @Embedded
    private OrderOption orderOption;

    private String name;

    public OrderOptionGroup(OrderOption orderOption, String name) {
        this.orderOption = orderOption;
        this.name = name;
    }

    public OptionGroupValidator convertToOptionGroupValidator() {
        return new OptionGroupValidator(name, orderOption.convertToOptionValidator());
    }

    protected void mapOrderLineItem(OrderLineItem orderLineItem) {
        this.orderLineItem = orderLineItem;
    }
}
