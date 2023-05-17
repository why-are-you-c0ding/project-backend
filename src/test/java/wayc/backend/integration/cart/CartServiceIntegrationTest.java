package wayc.backend.integration.cart;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import wayc.backend.cart.application.CartService;
import wayc.backend.cart.application.dto.request.RegisterCartLineItemRequestDto;
import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.repository.CartLineItemRepository;
import wayc.backend.cart.domain.repository.CartRepository;
import wayc.backend.factory.cart.CartFactory;
import wayc.backend.factory.cart.RegisterCartLineItemRequestFactory;
import wayc.backend.integration.IntegrationTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

public class CartServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartLineItemRepository cartLineItemRepository;

    @Autowired
    EntityManager em;

    @Test
    void registerCartLineItem(){

        //given
        Cart cart = CartFactory.createCart();
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.createSuccessCase().toServiceDto();
        cartRepository.save(cart);

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
        cartRepository.save(cart);
        cartService.registerCartLineItem(1L, dto);

        //when
        em.flush();
        cartService.updateCartLineItem(1L, cart.getCartLineItems().get(0).getId(), 3);

        //then
        assertThat(cart.getCartLineItems().get(0).getCount()).isEqualTo(3);
    }

    @Test
    void deleteCartLineItem(){

        //given
        Cart cart = CartFactory.createCart();
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.createSuccessCase().toServiceDto();
        cartRepository.save(cart);
        cartService.registerCartLineItem(1L, dto);

        //when
        em.flush();
        cartService.deleteCartLineItem(1L, cart.getCartLineItems().get(0).getId());

        //then
        assertThat(cart.getCartLineItems().size()).isEqualTo(0);
    }
}
