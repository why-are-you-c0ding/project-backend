package wayc.backend.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.base.BaseEntity;
import wayc.backend.shop.domain.join.Option;
import wayc.backend.shop.domain.join.OptionGroup;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OrderOptionGroup extends BaseEntity {

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

    public OptionGroup convertToOptionGroup() {
        return new OptionGroup(name, new Option(orderOptions.getOptionName(), orderOptions.getPrice()));
    }
}
