package wayc.backend.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.domain.BaseEntity;
import wayc.backend.shop.domain.port.OptionValidator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "option_spec")
@Entity
public class Option extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    @JoinColumn(name = "option_group_id")
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
