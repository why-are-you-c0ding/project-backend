package wayc.backend.pay.domain;

import wayc.backend.common.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pay extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Integer price;

    private Long orderId;

    protected Pay() {
    }
}
