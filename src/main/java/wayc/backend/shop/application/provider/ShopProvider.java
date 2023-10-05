package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wayc.backend.shop.application.dto.response.find.FindShopResponseDto;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.shop.exception.NotExistsShopException;

@Service
@RequiredArgsConstructor
public class ShopProvider {

    private final ShopRepository shopRepository;

    public FindShopResponseDto findShop(Long shopId){
        Shop shop = shopRepository.findByIdAndStatus(shopId).orElseThrow(NotExistsShopException::new);
        return FindShopResponseDto.of(shop);
    }
}
