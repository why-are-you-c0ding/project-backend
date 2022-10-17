package wayc.backend.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.base.BaseEntity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection //값 타입을 하나 이상 저장.
    @CollectionTable(name = "order_options", joinColumns = @JoinColumn(name="order_option_group_id")) //테이블을 만듬 이름과 조인 컬럼의 이름을ㅜ지정
    private List<OrderOption> orderOptions = new ArrayList<>();

    private String name;

    public OrderOptionGroup(String name, List<OrderOption> orderOptions) {
        this.name = name;
        this.orderOptions.addAll(orderOptions);
    }

    public OptionGroup convertToOptionGroup() {
        return new OptionGroup(name, orderOptions.stream().map(OrderOption::convertToOption).collect(toList()));
    }
}
