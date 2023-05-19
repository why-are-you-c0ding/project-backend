package wayc.backend.factory.cart;

import wayc.backend.cart.application.dto.request.RegisterCartLineItemRequestDto;
import wayc.backend.cart.application.dto.request.RegisterOptionGroupRequestDto;
import wayc.backend.cart.application.dto.request.RegisterOptionRequestDto;
import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.CartLineItem;

import java.util.List;

public class CartFactory {

    private CartFactory(){}

    public static Cart createCart(){
        return new Cart(1L);
    }

    public static RegisterCartLineItemRequestDto macBookLineItemDto(Long id){
        return new RegisterCartLineItemRequestDto(
                id,
                "맥북",
                1,
                List.of(
                new RegisterOptionGroupRequestDto(new RegisterOptionRequestDto("16GB", 80000), "RAM"),
                new RegisterOptionGroupRequestDto(new RegisterOptionRequestDto("512GB", 80000), "SSD")
                ),
                "imager.com"
        );
    }
}
