package wayc.backend.cart.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.domain.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class FindCartResponseDto {

    private List<FindCartLineItemResponseDto> cartLineItems = new ArrayList<>();


    public FindCartResponseDto(List<FindCartLineItemResponseDto> cartLineItems) {
        this.cartLineItems = cartLineItems;
    }

    public static FindCartResponseDto of(Cart cart) {
         return new FindCartResponseDto(
                 cart.getCartLineItems()
                         .stream()
                         .map(cartLineItem ->
                                 FindCartLineItemResponseDto.toCartLineItemResponseDto(cartLineItem))
                         .collect(Collectors.toList())
         );
    }
}
