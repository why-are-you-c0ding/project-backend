package wayc.backend.payment.domain;

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
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Integer pay;

    private Long orderId;

    private String platformTransactionId;

    public Payment(Integer pay, Long orderId, String platformTransactionId) {
        this.pay = pay;
        this.orderId = orderId;
        this.platformTransactionId = platformTransactionId;
    }
}


