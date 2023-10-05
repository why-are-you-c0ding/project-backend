package wayc.backend.pay.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;

import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.Pay;
import wayc.backend.order.domain.event.OrderPayedEvent;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.domain.repository.PayRepository;
import wayc.backend.order.exception.NotExistsOrderException;

@Getter
@RequiredArgsConstructor
public class CompletePayWithCreatedOrderEventHandler {

    private final OrderRepository orderRepository;
    private final PayRepository payRepository;

    @EventListener(OrderPayedEvent.class)
    public void handle(OrderPayedEvent event){

        Order order = orderRepository.findOrderById(event.getOrderId())
                .orElseThrow(NotExistsOrderException::new);

        //TODO: 외부 결제사 연동.
        Pay pay = new Pay(event.getPayment(), order.getId());
        payRepository.save(pay);
    }
}
