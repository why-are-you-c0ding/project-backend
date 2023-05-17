package wayc.backend.pay.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;

import org.springframework.transaction.event.TransactionalEventListener;
import wayc.backend.order.application. OrderCreatedEvent;
import wayc.backend.pay.application.PayService;
import wayc.backend.pay.application.dto.request.CreatePayRequestDto;

@Getter
@RequiredArgsConstructor
public class CompletePayWithCreatedOrderEventHandler {

    private final PayService payService;

    @TransactionalEventListener(OrderCreatedEvent.class)
    public void handle(OrderCreatedEvent event){
        payService.createPay(event.getOrderingMemberId(), new CreatePayRequestDto(event.getOrderId(), event.getPayment()));
    }
}
