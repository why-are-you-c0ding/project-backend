package wayc.backend.cart.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import wayc.backend.cart.application.CartProvider;
import wayc.backend.cart.application.CartService;
import wayc.backend.cart.application.dto.response.FindCartResponseDto;

import wayc.backend.cart.presentation.dto.request.DeleteCartLineItemRequest;
import wayc.backend.cart.presentation.dto.request.UpdateCartLineItemRequest;
import wayc.backend.cart.presentation.dto.request.RegisterCartLineItemRequest;
import wayc.backend.cart.presentation.dto.response.DeleteCartLineItemResponse;
import wayc.backend.cart.presentation.dto.response.UpdateCartLineItemResponse;
import wayc.backend.cart.presentation.dto.response.RegisterCartLineItemResponse;
import wayc.backend.security.UserPrincipal;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartProvider cartProvider;

    @PostMapping("/cart-line-items")
    public ResponseEntity registerCartLineItem(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                               @RequestBody RegisterCartLineItemRequest request) {
        cartService.registerCartLineItem(userPrincipal.getId(), request.toServiceDto());
        return new ResponseEntity(new RegisterCartLineItemResponse(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity findCart(@AuthenticationPrincipal UserPrincipal userPrincipal){
        FindCartResponseDto res = cartProvider.findCart(userPrincipal.getId());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping("/cart-line-items")
    public ResponseEntity updateCartLineItem(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                             @RequestBody UpdateCartLineItemRequest request){
        cartService.updateCartLineItem(userPrincipal.getId(), request.getCartLineItemId(), request.getCount());
        return new ResponseEntity(new UpdateCartLineItemResponse(), HttpStatus.OK);
    }

    @DeleteMapping("/cart-line-items")
    public ResponseEntity updateCartLineItem(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                             @RequestBody DeleteCartLineItemRequest request){
        cartService.deleteCartLineItem(userPrincipal.getId(), request.getCartLineItemId());
        return new ResponseEntity(new DeleteCartLineItemResponse(), HttpStatus.OK);
    }
}
