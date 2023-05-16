package wayc.backend.cart.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import wayc.backend.cart.application.CartService;
import wayc.backend.cart.application.dto.response.FindCartResponseDto;

import wayc.backend.cart.presentation.dto.request.DeleteCartLineItemRequest;
import wayc.backend.cart.presentation.dto.request.UpdateCartLineItemRequest;
import wayc.backend.cart.presentation.dto.request.RegisterCartLineItemRequest;
import wayc.backend.cart.presentation.dto.response.DeleteCartLineItemResponse;
import wayc.backend.cart.presentation.dto.response.UpdateCartLineItemResponse;
import wayc.backend.cart.presentation.dto.response.RegisterCartLineItemResponse;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart-line-items")
    public ResponseEntity registerCartLineItem(@AuthenticationPrincipal Long id,
                                             @RequestBody RegisterCartLineItemRequest request
    ) {
        cartService.registerCartLineItem(id, request.toServiceDto());
        return new ResponseEntity(new RegisterCartLineItemResponse(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity findCart(@AuthenticationPrincipal Long id){
        FindCartResponseDto res = cartService.findCart(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping("/cart-line-items")
    public ResponseEntity updateCartLineItem(@AuthenticationPrincipal Long id,
                                             @RequestBody UpdateCartLineItemRequest request){
        cartService.updateCartLineItem(id, request.getCartLineItemId(), request.getCount());
        return new ResponseEntity(new UpdateCartLineItemResponse(), HttpStatus.OK);
    }

    @DeleteMapping("/cart-line-items")
    public ResponseEntity updateCartLineItem(@AuthenticationPrincipal Long id,
                                             @RequestBody DeleteCartLineItemRequest request){
        cartService.deleteCartLineItem(id, request.getCartLineItemId());
        return new ResponseEntity(new DeleteCartLineItemResponse(), HttpStatus.OK);
    }
}
