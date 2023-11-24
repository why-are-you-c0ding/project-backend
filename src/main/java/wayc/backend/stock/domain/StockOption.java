package wayc.backend.stock.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.context.annotation.Configuration;
import wayc.backend.common.domain.BaseEntity;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class StockOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Stock stock;

    @Column(name = "item_option_id")
    private Long optionId;

    public StockOption(Stock stock, Long optionId) {
        this.stock = stock;
        this.optionId = optionId;
    }
}
