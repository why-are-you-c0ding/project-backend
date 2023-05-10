package wayc.backend.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.domain.BaseEntity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Stock extends BaseEntity { //옵션과 Stock은 N:M 인듯

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<StockOptionSpecification> stockOptionSpecifications = new ArrayList<>();

    private Integer quantity;


    public Stock(List<OptionSpecification> options, Integer quantity) {
        this.stockOptionSpecifications = options
                .stream()
                .map(option-> new StockOptionSpecification(this, option))
                .collect(Collectors.toList());
        this.quantity = quantity;
    }
}
