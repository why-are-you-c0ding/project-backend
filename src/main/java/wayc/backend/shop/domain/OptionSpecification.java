package wayc.backend.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OptionSpecification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "option_group_specification_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OptionGroupSpecification optionGroupSpecification;

    private String name;

    private Integer price;

    public OptionSpecification(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    /**
     * validation 로직 추가
     */
}
