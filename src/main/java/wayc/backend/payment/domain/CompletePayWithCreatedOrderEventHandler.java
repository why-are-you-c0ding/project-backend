package wayc.backend.payment.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;

import wayc.backend.order.domain.event.OrderPayedEvent;

@Getter
@RequiredArgsConstructor
public class CompletePayWithCreatedOrderEventHandler {


    @EventListener(OrderPayedEvent.class)
    public void handle(OrderPayedEvent event){

    }
}
