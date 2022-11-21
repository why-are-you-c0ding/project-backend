package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.exception.shop.NotExistsShopException;
import wayc.backend.shop.application.dto.response.show.ShowShopResponseDto;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.shop.domain.Shop;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    @Transactional(readOnly = false)
    public void createShop(Long ownerId, String nickName){
        Shop shop = Shop.builder().ownerId(ownerId).shopName(nickName + "님의 shop").build();
        shopRepository.save(shop);
    }

    public ShowShopResponseDto getShop(Long shopId){
        Shop shop = shopRepository.findByIdAndStatus(shopId)
                .orElseThrow(NotExistsShopException::new);
        return ShowShopResponseDto.of(shop);
    }

}
