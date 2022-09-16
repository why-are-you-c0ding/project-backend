package wayc.backend.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.base.BaseEntity;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class StockOptionSpecification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "stock_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Stock stock;

    @JoinColumn(name = "option_specification_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private OptionSpecification optionSpecification;

}
