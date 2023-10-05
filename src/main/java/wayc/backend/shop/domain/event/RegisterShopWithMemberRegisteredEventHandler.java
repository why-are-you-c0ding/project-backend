package wayc.backend.shop.domain.event;

import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import wayc.backend.member.domain.event.MemberRegisteredEvent;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ShopRepository;

import static wayc.backend.member.domain.Role.*;

@Component
@RequiredArgsConstructor
public class RegisterShopWithMemberRegisteredEventHandler {

    private final ShopRepository shopRepository;

    @EventListener(MemberRegisteredEvent.class)
    public void handle(MemberRegisteredEvent event){
        if(event.getRole() == ROLE_SELLER){
            Shop shop = Shop.builder().ownerId(event.getMemberId()).name(event.getMemberName() + "님의 shop").build();
            shopRepository.save(shop);
        }
    }
}
