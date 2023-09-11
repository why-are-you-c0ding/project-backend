package wayc.backend.pay.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;

import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.order.domain.Pay;
import wayc.backend.order.domain.event.OrderPayedEvent;
import wayc.backend.order.domain.repository.OrderLineItemRepository;
import wayc.backend.order.domain.repository.PayRepository;
import wayc.backend.order.exception.NotExistsOrderException;

@Getter
@RequiredArgsConstructor
public class CompletePayWithCreatedOrderEventHandler {

    private final OrderLineItemRepository orderRepository;
    private final PayRepository payRepository;

    @EventListener(OrderPayedEvent.class)
    public void handle(OrderPayedEvent event){

        OrderLineItem order = orderRepository.findOrderByOrderIdAndOrderingMemberIdAndOrderStatus(event.getOrderId(), event.getOrderingMemberId())
                .orElseThrow(NotExistsOrderException::new);

        //TODO: 외부 결제사 연동.

        Pay pay = new Pay(event.getPayment(), order.getId());
        payRepository.save(pay);

        //아래도 분리해야할 거 같은데..?!
        order.updateOrder(OrderStatus.ONGOING);
    }
}
