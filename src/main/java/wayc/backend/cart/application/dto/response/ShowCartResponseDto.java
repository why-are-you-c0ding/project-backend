package wayc.backend.cart.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.domain.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ShowCartResponseDto {

    private List<ShowLineItemResponseDto> cartLineItems = new ArrayList<>();


    public ShowCartResponseDto(List<ShowLineItemResponseDto> cartLineItems) {
        this.cartLineItems = cartLineItems;
    }

    public static ShowCartResponseDto of(Cart cart) {
         return new ShowCartResponseDto(
                 cart
                         .getCartLineItems()
                         .stream()
                         .map(cartLineItem ->
                                 ShowLineItemResponseDto.toLineItemResponseDto(cartLineItem))
                         .collect(Collectors.toList())
         );
    }
}
