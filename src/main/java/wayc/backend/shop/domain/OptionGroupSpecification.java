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
@Entity
public class OptionGroupSpecification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "option_group_specification_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<OptionSpecification> optionSpecifications = new ArrayList<>();

    private String name;

    private Boolean basic;

    public OptionGroupSpecification(List<OptionSpecification> optionSpecifications, String name, Boolean basic) {
        this.optionSpecifications = optionSpecifications;
        this.name = name;
        this.basic = basic;
    }



    public Integer getBasicPrice(){
        if(basic == true){
            return optionSpecifications.get(0).getPrice();
        }
        return -1;
    }

    /**
     * validation 로직 추가
     */

}
