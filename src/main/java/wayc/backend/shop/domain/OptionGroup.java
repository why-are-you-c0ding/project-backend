package wayc.backend.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.domain.BaseEntity;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Option> options = new ArrayList<>();

    private String name;

    private Boolean basic;

    public OptionGroup(List<Option> options, String name, Boolean basic) {
        this.options = options;
        this.name = name;
        this.basic = basic;
        options.forEach(option -> option.add(this));
    }

    public Integer getBasicPrice(){
        if(basic == true){
            return options.get(0).getPrice();
        }
        return -1;
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
        if (!name.equals(groupName)) {
            return false;
        }

        if (satisfied.isEmpty()) {
            return false;
        }

        if (satisfied.size() > 1) {
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
