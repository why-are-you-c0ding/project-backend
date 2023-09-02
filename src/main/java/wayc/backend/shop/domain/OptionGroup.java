package wayc.backend.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.domain.BaseEntity;
import wayc.backend.shop.domain.valid.OptionGroupValidator;
import wayc.backend.shop.domain.valid.OptionValidator;
import wayc.backend.stock.domain.StockOption;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "option_group_spec")
@Entity
public class OptionGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "optionGroup")
    private List<Option> options = new ArrayList<>();

    private String name;

    public OptionGroup(List<Option> options, String name) {
        this.options = options;
        this.name = name;
        options.forEach(option -> option.add(this));
    }

    public void add(Item item) {
        this.item = item;
    }

    /**
     * validation 로직 추가
     */

    public boolean isSatisfiedBy(OptionGroupValidator optionGroupValidator) {
        return isSatisfied(optionGroupValidator.getName(), satisfied(optionGroupValidator.getOption()));
    }

    private boolean isSatisfied(String groupName, List<Option> satisfied) {
        if (!name.equals(groupName)) { //그룹 이름이 다르면 문제가 있음.
            return false;
        }

        if (satisfied.isEmpty()) {  //하나도 검증이 안되면 문제가 있음
            return false;
        }

        if (satisfied.size() > 1) {  //1개 이상이 같으면 문제가 있음
            return false;
        }

        return true;
    }

    private List<Option> satisfied(OptionValidator optionValidator) {
        return options
                .stream()
                .filter(option -> option.isSatisfiedBy(optionValidator))
                .collect(toList());
    }

}
