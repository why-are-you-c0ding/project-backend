package wayc.backend.order.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.event.Event;

import java.util.List;

@Getter
@NoArgsConstructor
public class DecreasedStockEvent extends Event {

    private Integer orderQuantity;
    private List<Long> orderOptionIds;

    public DecreasedStockEvent(Integer orderQuantity, List<Long> orderOptionIds) {
        this.orderQuantity = orderQuantity;
        this.orderOptionIds = orderOptionIds;
    }
}
