package wayc.backend.factory.cart;

import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.CartLineItem;

public class CartFactory {

    private CartFactory(){}

    public static Cart createCart(){
        return new Cart(1L);
    }
}
