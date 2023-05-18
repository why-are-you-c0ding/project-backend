package wayc.backend.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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


    @OneToMany(mappedBy = "option")
    private List<StockOption> stockOptions = new ArrayList<>();

    public void add(OptionGroup optionGroup) {
        this.optionGroup = optionGroup;
    }


    /**
     * validation 로직 추가
     */
}
