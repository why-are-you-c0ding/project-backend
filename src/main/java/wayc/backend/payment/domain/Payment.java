package wayc.backend.payment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.common.domain.Money;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderLineItem;

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

    private Money amount;

    private Long orderId;

    private String platformTransactionId;

    public Payment(Integer amount, Long orderId, String platformTransactionId) {
        this.amount = Money.from(amount);
        this.orderId = orderId;
        this.platformTransactionId = platformTransactionId;
    }

    public void delete(Order order) {
        delete();
        order.cancelPayment();
    }

    public void refund(OrderLineItem orderLineItem) {
        this.amount = amount.minus(orderLineItem.calculatePrice());
        orderLineItem.refund();
    }
}


