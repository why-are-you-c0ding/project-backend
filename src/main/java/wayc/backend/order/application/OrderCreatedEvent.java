package wayc.backend.order.application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.event.Event;

@Getter
@NoArgsConstructor
public class OrderCreatedEvent extends Event {
    private Long orderingMemberId;
    private Long orderId;
    private Integer payment;

    public OrderCreatedEvent(Long orderingMemberId,
                             Long orderId,
                             Integer payment) {
        this.orderingMemberId = orderingMemberId;
        this.orderId = orderId;
        this.payment = payment;
    }
}
