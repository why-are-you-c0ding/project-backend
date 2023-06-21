package wayc.backend.integration.cart;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import wayc.backend.cart.application.CartMapper;
import wayc.backend.cart.application.CartService;
import wayc.backend.cart.application.dto.request.RegisterCartLineItemRequestDto;
import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.CartLineItem;
import wayc.backend.cart.domain.repository.CartLineItemRepository;
import wayc.backend.cart.domain.repository.CartRepository;
import wayc.backend.factory.Item.ItemFactory;
import wayc.backend.factory.cart.CartFactory;
import wayc.backend.factory.cart.RegisterCartLineItemRequestFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.command.ItemRepository;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

public class CartServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    EntityManager em;

    @Test
    void registerCartLineItem(){

        //given
        Item item = itemRepository.save(ItemFactory.createMacBook());
        Cart cart = CartFactory.createCart();
        cartRepository.save(cart);
        RegisterCartLineItemRequestDto dto = CartFactory.macBookLineItemDto(item.getId());

        //when
        cartService.registerCartLineItem(1L, dto);

        //then
        assertThat(cart.getCartLineItems().size()).isEqualTo(1);
    }

    @Test
    void updateCartLineItem(){

        //given
        Item item = itemRepository.save(ItemFactory.createMacBook());
        Cart cart = CartFactory.createCart();
        cartRepository.save(cart);
        RegisterCartLineItemRequestDto dto = CartFactory.macBookLineItemDto(item.getId());
        cartService.registerCartLineItem(1L, dto);

        //when
        em.flush();
        cartService.updateCartLineItem(cart.getCartOwner().getMemberId(), cart.getCartLineItems().get(0).getId(), 3);

        //then
        assertThat(cart.getCartLineItems().get(0).getCount()).isEqualTo(3);
    }

    @Test
    void deleteCartLineItem(){

        //given
        Item item = itemRepository.save(ItemFactory.createMacBook());
        Cart cart = CartFactory.createCart();
        cartRepository.save(cart);
        RegisterCartLineItemRequestDto dto = CartFactory.macBookLineItemDto(item.getId());
        cartService.registerCartLineItem(1L, dto);

        //when
        em.flush();
        cartService.deleteCartLineItem(cart.getCartOwner().getMemberId(), cart.getCartLineItems().get(0).getId());

        //then
        assertThat(cart.getCartLineItems().size()).isEqualTo(0);
    }


}
