package wayc.backend.pay.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.pay.infrastructure.kakaopay.KakaoPayProperties;

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

    private Integer pay;

    private Long orderId;

    public Pay(Integer pay, Long orderId) {
        this.pay = pay;
        this.orderId = orderId;
    }
}


