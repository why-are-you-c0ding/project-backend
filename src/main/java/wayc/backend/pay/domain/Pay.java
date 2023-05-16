package wayc.backend.pay.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pay extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Integer price;

    private Long orderId;

    public Pay(Integer price, Long orderId) {
        this.price = price;
        this.orderId = orderId;
    }
}


