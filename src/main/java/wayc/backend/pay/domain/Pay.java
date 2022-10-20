package wayc.backend.pay.domain;

import lombok.Getter;
import wayc.backend.common.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Pay extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Integer price;

    private Long orderId;

    protected Pay() {
    }

    public Pay(Integer price, Long orderId) {
        this.price = price;
        this.orderId = orderId;
    }
}


