package wayc.backend.integration.cart;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import wayc.backend.cart.application.CartMapper;
import wayc.backend.cart.application.CartProvider;
import wayc.backend.cart.application.CartService;
import wayc.backend.cart.application.dto.request.RegisterCartLineItemRequestDto;
import wayc.backend.cart.application.dto.response.FindCartResponseDto;
import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.CartLineItem;
import wayc.backend.cart.domain.repository.CartLineItemRepository;
import wayc.backend.cart.domain.repository.CartRepository;
import wayc.backend.factory.Item.ItemFactory;
import wayc.backend.factory.cart.RegisterCartLineItemRequestFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.command.ItemRepository;

public class CartProviderIntegrationTest extends IntegrationTest {

    @Autowired
    private CartProvider cartProvider;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartLineItemRepository cartLineItemRepository;

    @Test
    @DisplayName("카트의 내용을 조회한다.")
    void findCart(){

        //given
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.registerMacBook().toServiceDto();
        itemRepository.save(ItemFactory.createMacBook());
        Cart cart = cartRepository.save(new Cart(1L));
        CartLineItem lineItem = cartLineItemRepository.save(new CartMapper().toLineItem(dto, cart));
        cart.addCartLineItem(lineItem);

        //when
        FindCartResponseDto result = cartProvider.findCart(1L);

        //then
        Assertions.assertThat(result.getCartLineItems().size()).isEqualTo(1);
    }
}
