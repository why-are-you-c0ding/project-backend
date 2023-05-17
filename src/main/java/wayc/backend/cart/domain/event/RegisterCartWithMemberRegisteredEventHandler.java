package wayc.backend.cart.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import wayc.backend.cart.application.CartService;
import wayc.backend.member.domain.event.MemberRegisteredEvent;


@Component
@RequiredArgsConstructor
public class RegisterCartWithMemberRegisteredEventHandler {

    private final CartService cartService;

    @TransactionalEventListener(MemberRegisteredEvent.class)
    public void handle(MemberRegisteredEvent event){
        cartService.register(event.getMemberId());
    }
}
