package wayc.backend.shop.domain.event;

import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import wayc.backend.member.domain.event.MemberRegisteredEvent;
import wayc.backend.shop.application.ShopService;

import static wayc.backend.security.role.Role.*;

@Component
@RequiredArgsConstructor
public class RegisterShopWithMemberRegisteredEventHandler {

    private final ShopService shopService;

    @EventListener(MemberRegisteredEvent.class)
    public void handle(MemberRegisteredEvent event){
        if(event.getRole() == ROLE_SELLER){
            shopService.registerShop(event.getMemberId(), event.getMemberName());
        }
    }
}
