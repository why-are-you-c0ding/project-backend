package wayc.backend.cart.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wayc.backend.cart.application.CartService;
import wayc.backend.cart.application.dto.response.ShowCartResponseDto;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<ShowCartResponseDto> showCart(
            @AuthenticationPrincipal Long id
    ){
        ShowCartResponseDto res = cartService.show(id);
        return ResponseEntity.ok(res);
    }

}
