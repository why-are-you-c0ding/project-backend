package wayc.backend.shop.application.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.shop.exception.NotExistsShopException;
import wayc.backend.shop.application.dto.response.find.FindShopResponseDto;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.shop.domain.Shop;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void registerShop(Long ownerId, String nickName) {
        Shop shop = Shop.builder().ownerId(ownerId).shopName(nickName + "님의 shop").build();
        shopRepository.save(shop);
    }
}
