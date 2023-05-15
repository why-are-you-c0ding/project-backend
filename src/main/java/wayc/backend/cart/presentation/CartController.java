package wayc.backend.cart.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import wayc.backend.cart.application.CartService;
import wayc.backend.cart.application.dto.response.ShowCartResponseDto;

import wayc.backend.cart.presentation.dto.request.DeleteCartLineItemRequestDto;
import wayc.backend.cart.presentation.dto.request.PatchCartLineItemRequestDto;
import wayc.backend.cart.presentation.dto.request.PostCartLineItemRequestDto;
import wayc.backend.cart.presentation.dto.response.DeleteCartLineItemResponseDto;
import wayc.backend.cart.presentation.dto.response.PatchCartLineItemResponseDto;
import wayc.backend.cart.presentation.dto.response.PostCartLineItemResponseDto;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart-line-items")
    public ResponseEntity createCartLineItem(@AuthenticationPrincipal Long id,
                                             @RequestBody PostCartLineItemRequestDto request
    ) {
        cartService.createCartLineItem(id, request.toServiceDto());
        return new ResponseEntity(new PostCartLineItemResponseDto(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity showCart(@AuthenticationPrincipal Long id){
        ShowCartResponseDto res = cartService.show(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping("/cart-line-items")
    public ResponseEntity updateCartLineItem(@AuthenticationPrincipal Long id,
                                             @RequestBody PatchCartLineItemRequestDto request){
        cartService.updateCartLineItem(id, request.getCartLineItemId(), request.getCount());
        return new ResponseEntity(new PatchCartLineItemResponseDto(), HttpStatus.OK);
    }

    @DeleteMapping("/cart-line-items")
    public ResponseEntity updateCartLineItem(@AuthenticationPrincipal Long id,
                                             @RequestBody DeleteCartLineItemRequestDto request){
        cartService.deleteCartLineItem(id, request.getCartLineItemId());
        return new ResponseEntity(new DeleteCartLineItemResponseDto(), HttpStatus.OK);
    }
}
