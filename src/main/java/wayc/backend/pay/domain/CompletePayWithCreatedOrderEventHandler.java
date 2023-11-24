package wayc.backend.pay.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;

import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.event.OrderPayedEvent;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.exception.NotExistsOrderException;

@Getter
@RequiredArgsConstructor
public class CompletePayWithCreatedOrderEventHandler {


    @EventListener(OrderPayedEvent.class)
    public void handle(OrderPayedEvent event){

    }
}
