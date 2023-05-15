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
public class OptionSpecification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    public OptionSpecification(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    @OneToMany(mappedBy = "optionSpecification")
    private List<StockOptionSpecification> stockOptionSpecifications = new ArrayList<>();


    /**
     * validation 로직 추가
     */
}
