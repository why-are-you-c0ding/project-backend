package wayc.backend.pay.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;

import wayc.backend.order.application.OrderCreatedEvent;
import wayc.backend.pay.application.PayService;
import wayc.backend.pay.application.dto.request.CreatePayRequestDto;

@Getter
@RequiredArgsConstructor
public class CompletePayWithCreatedOrderEventHandler {

    private final PayService payService;

    @EventListener(OrderCreatedEvent.class)
    public void handle(OrderCreatedEvent event){
        payService.createPay(event.getOrderingMemberId(), new CreatePayRequestDto(event.getOrderId(), event.getPayment()));
    }
}
