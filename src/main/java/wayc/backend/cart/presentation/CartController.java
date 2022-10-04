package wayc.backend.cart.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import wayc.backend.cart.application.CartService;
import wayc.backend.cart.application.dto.response.ShowCartResponseDto;

import wayc.backend.cart.presentation.dto.request.PostCartLineItemRequestDto;
import wayc.backend.cart.presentation.dto.response.PostCartLineItemResponseDto;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart-line-items")
    public ResponseEntity<PostCartLineItemResponseDto> createCartLineItem(
            @AuthenticationPrincipal Long id,
            @RequestBody PostCartLineItemRequestDto request
            ){
        cartService.createCartLineItem(id, request.toServiceDto());
        return ResponseEntity.ok(new PostCartLineItemResponseDto());
    }

    @GetMapping
    public ResponseEntity<ShowCartResponseDto> showCart(
            @AuthenticationPrincipal Long id
    ){
        ShowCartResponseDto res = cartService.show(id);
        return ResponseEntity.ok(res);
    }

}
