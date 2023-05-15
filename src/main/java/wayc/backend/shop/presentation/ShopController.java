package wayc.backend.shop.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import wayc.backend.shop.application.ShopService;
import wayc.backend.shop.application.dto.response.find.FindShopResponseDto;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<FindShopResponseDto> findShop(@PathVariable Long shopId){
        FindShopResponseDto res = shopService.showShop(shopId);
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
