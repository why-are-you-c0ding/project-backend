package wayc.backend.shop.domain.event;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import wayc.backend.member.domain.event.MemberRegisteredEvent;
import wayc.backend.shop.application.service.ShopService;

import static wayc.backend.security.role.Role.*;

@Component
@RequiredArgsConstructor
public class RegisterShopWithMemberRegisteredEventHandler {

    private final ShopService shopService;

    @TransactionalEventListener(MemberRegisteredEvent.class)
    public void handle(MemberRegisteredEvent event){
        if(event.getRole() == ROLE_SELLER){
            shopService.registerShop(event.getMemberId(), event.getMemberName());
        }
    }
}
