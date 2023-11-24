package wayc.backend.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.domain.BaseEntity;
import wayc.backend.shop.domain.valid.OptionValidator;

import javax.persistence.*;
import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "item_option")
@Entity
public class Option extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    @JoinColumn(name = "item_option_group_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private OptionGroup optionGroup;

    public Option(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public void add(OptionGroup optionGroup) {
        this.optionGroup = optionGroup;
    }

    public boolean isSatisfiedBy(OptionValidator option) {
        if(name.equals(option.getName()) && Objects.equals(price, option.getPrice())) return true;
        return false;
    }
}
