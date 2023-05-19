package wayc.backend.cart.application;

import org.springframework.stereotype.Component;

import wayc.backend.cart.application.dto.request.RegisterCartLineItemRequestDto;
import wayc.backend.cart.application.dto.request.RegisterOptionGroupRequestDto;
import wayc.backend.cart.application.dto.request.RegisterOptionRequestDto;

import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.CartLineItem;
import wayc.backend.cart.domain.CartOption;
import wayc.backend.cart.domain.CartOptionGroup;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartLineItem toLineItem(RegisterCartLineItemRequestDto dto, Cart cart) {
        return CartLineItem
                .builder()
                .itemId(dto.getItemId())
                .cart(cart)
                .name(dto.getName())
                .count(dto.getCount())
                .imageUrl(dto.getImageUrl())
                .cartOptionGroups(
                        dto.getCartOptionGroups()
                                .stream()
                                .map(this::toCartOptionGroup)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private CartOptionGroup toCartOptionGroup(RegisterOptionGroupRequestDto dto) {
        CartOptionGroup optionGroup = CartOptionGroup
                .builder()
                .name(dto.getName())
                .cartOption(
                        toCartOption(dto.getCartOptions())
                )
                .build();

        return optionGroup;
    }

    private CartOption toCartOption(RegisterOptionRequestDto dto) {
        return new CartOption(dto.getName(), dto.getPrice());
    }

}
