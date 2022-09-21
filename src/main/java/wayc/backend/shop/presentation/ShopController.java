package wayc.backend.shop.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import wayc.backend.shop.application.ShopService;
import wayc.backend.shop.application.dto.response.ShowShopResponseDto;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<ShowShopResponseDto> showShop(@PathVariable Long shopId){
        ShowShopResponseDto res = shopService.getShop(shopId);
        return ResponseEntity.ok(res);
    }
}
