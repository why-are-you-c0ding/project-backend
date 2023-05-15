package wayc.backend.cart.application;

import org.springframework.stereotype.Component;
import wayc.backend.cart.application.dto.request.CreateCartLineItemRequestDto;
import wayc.backend.cart.application.dto.request.CreateOptionGroupRequestDto;
import wayc.backend.cart.application.dto.request.CreateOptionRequestDto;

import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.CartLineItem;
import wayc.backend.cart.domain.CartOption;
import wayc.backend.cart.domain.CartOptionGroup;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartLineItem toLineItem(CreateCartLineItemRequestDto dto, Cart cart) {
        CartLineItem cartLineItem = CartLineItem
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
        cart.add(cartLineItem);
        return cartLineItem;
    }

    private CartOptionGroup toCartOptionGroup(CreateOptionGroupRequestDto dto) {
        CartOptionGroup optionGroup = CartOptionGroup
                .builder()
                .name(dto.getName())
                .cartOptions(
                        dto
                                .getCartOptions()
                                .stream()
                                .map(this::toCartOption)
                                .collect(Collectors.toList())
                )
                .build();

        return optionGroup;
    }

    private CartOption toCartOption(CreateOptionRequestDto dto) {
        return new CartOption(dto.getName(), dto.getPrice());
    }
}
