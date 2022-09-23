package wayc.backend.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OptionGroupSpecification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "option_group_specification_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<OptionSpecification> optionSpecifications = new ArrayList<>();

    /**
     * validation 로직 추가
     */

    public OptionGroupSpecification(List<OptionSpecification> optionSpecifications) {
        this.optionSpecifications = optionSpecifications;
    }

}
