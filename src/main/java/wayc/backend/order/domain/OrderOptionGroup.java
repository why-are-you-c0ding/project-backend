package wayc.backend.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.shop.domain.port.OptionGroupComparator;
import wayc.backend.shop.domain.port.OptionGroupValidator;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OrderOptionGroup extends BaseEntity implements OptionGroupComparator {

    @Id
    @Column(name = "order_option_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OrderOption orderOptions;

    private String name;

    public OrderOptionGroup(OrderOption orderOption, String name) {
        this.orderOptions = orderOption;
        this.name = name;
    }

    public OptionGroupValidator convertToOptionGroupValidator() {
        return new OptionGroupValidator(name, orderOptions.convertToOptionValidator());
    }
}
