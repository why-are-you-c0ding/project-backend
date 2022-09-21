package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.shop.dataaccess.ShopRepository;
import wayc.backend.shop.domain.Shop;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    @Transactional(readOnly = false)
    public void createShop(Long ownerId, String nickName){
        Shop shop = Shop.builder().ownerId(ownerId).shopName(nickName + "님의 shop").build();
        shopRepository.save(shop);
    }

}
