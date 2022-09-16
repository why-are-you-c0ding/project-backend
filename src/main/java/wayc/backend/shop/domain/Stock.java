package wayc.backend.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.basedomain.BaseEntity;

import javax.persistence.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Stock extends BaseEntity { //옵션과 Stock은 N:M 인듯

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<StockOptionSpecification> stockOptionSpecifications;

    private Integer stock;
}
