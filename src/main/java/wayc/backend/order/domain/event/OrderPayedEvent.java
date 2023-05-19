package wayc.backend.order.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.event.Event;

@Getter
@NoArgsConstructor
public class OrderPayedEvent extends Event {
    private Long orderingMemberId;
    private Long orderId;
    private Integer payment;

    public OrderPayedEvent(Long orderingMemberId,
                           Long orderId,
                           Integer payment) {
        this.orderingMemberId = orderingMemberId;
        this.orderId = orderId;
        this.payment = payment;
    }
}
