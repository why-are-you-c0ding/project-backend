package wayc.backend.unit.application.cart;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import wayc.backend.cart.application.CartMapper;
import wayc.backend.cart.application.CartService;
import wayc.backend.cart.application.dto.request.RegisterCartLineItemRequestDto;
import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.CartLineItem;
import wayc.backend.cart.domain.repository.CartLineItemRepository;
import wayc.backend.cart.domain.repository.CartRepository;
import wayc.backend.factory.cart.CartFactory;
import wayc.backend.factory.cart.RegisterCartLineItemRequestFactory;
import wayc.backend.unit.application.UnitTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class CartServiceTest extends UnitTest {

   CartService cartService;

   @Mock
   CartRepository cartRepository;

   @Mock
   CartLineItemRepository cartLineItemRepository;

    @BeforeEach
    void beforeEach(){
        this.cartService = new CartService(cartRepository, cartLineItemRepository, new CartMapper());
    }

    @Test
    void registerCartLineItem(){

        //given
        Cart cart = CartFactory.createCart();
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.createSuccessCase().toServiceDto();
        given(cartRepository.findByIdAndStatus(1L)).willReturn(Optional.of(cart));

        //when
        cartService.registerCartLineItem(1L, dto);

        //then
        assertThat(cart.getCartLineItems().size()).isEqualTo(1);
    }

    @Test
    void updateCartLineItem(){

        //given
        Cart cart = CartFactory.createCart();
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.createSuccessCase().toServiceDto();
        CartLineItem cartLineItem = new CartMapper().toLineItem(dto, cart);
        cart.addCartLineItem(cartLineItem);

        given(cartRepository.findByIdAndStatus(Mockito.anyLong())).willReturn(Optional.of(cart));
        given(cartLineItemRepository.findByIdAndStatus(Mockito.anyLong())).willReturn(Optional.of(cartLineItem));

        //when
        cartService.updateCartLineItem(1L, 2L, 3);

        //then
        assertThat(cart.getCartLineItems().get(0).getCount()).isEqualTo(3);
    }

    @Test
    void deleteCartLineItem(){

        //given
        Cart cart = CartFactory.createCart();
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.createSuccessCase().toServiceDto();
        CartLineItem cartLineItem = new CartMapper().toLineItem(dto, cart);
        cart.addCartLineItem(cartLineItem);

        given(cartRepository.findByIdAndStatus(Mockito.anyLong())).willReturn(Optional.of(cart));
        given(cartLineItemRepository.findByIdAndStatus(Mockito.anyLong())).willReturn(Optional.of(cartLineItem));

        //when
        cartService.deleteCartLineItem(1L, 2L);

        //then
        assertThat(cart.getCartLineItems().size()).isEqualTo(0);
    }
}
