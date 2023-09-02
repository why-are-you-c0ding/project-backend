package wayc.backend.cart.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import wayc.backend.cart.application.CartService;
import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.repository.CartRepository;
import wayc.backend.member.domain.event.MemberRegisteredEvent;


@Component
@RequiredArgsConstructor
public class RegisterCartWithMemberRegisteredEventHandler {

    private final CartRepository cartRepository;

    @EventListener(MemberRegisteredEvent.class)
    public void handle(MemberRegisteredEvent event){
        cartRepository.save(new Cart(event.getMemberId()));
    }
}
