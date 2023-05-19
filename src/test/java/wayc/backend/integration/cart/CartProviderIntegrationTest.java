package wayc.backend.integration.cart;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import wayc.backend.cart.application.CartProvider;
import wayc.backend.cart.application.CartService;
import wayc.backend.cart.application.dto.request.RegisterCartLineItemRequestDto;
import wayc.backend.cart.application.dto.response.FindCartResponseDto;
import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.repository.CartRepository;
import wayc.backend.factory.Item.ItemFactory;
import wayc.backend.factory.cart.RegisterCartLineItemRequestFactory;
import wayc.backend.integration.IntegrationTest;

public class CartProviderIntegrationTest extends IntegrationTest {

    @Autowired
    private CartProvider cartProvider;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Test
    @DisplayName("카트의 내용을 조회한다.")
    void findCart(){

        //given
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.createSuccessCase().toServiceDto();

        cartRepository.save(new Cart(1L));

        cartService.registerCartLineItem(1L, dto);

        //when
        FindCartResponseDto result = cartProvider.findCart(1L);

        //then
        Assertions.assertThat(result.getCartLineItems().size()).isEqualTo(1);
    }
}
