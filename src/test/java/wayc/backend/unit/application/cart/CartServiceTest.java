package wayc.backend.unit.application.cart;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
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

import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.port.ItemComparisonValidator;

import wayc.backend.unit.application.UnitTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class CartServiceTest extends UnitTest {

   CartService cartService;

   @Spy
   CartRepository cartRepository;

   @Spy
   CartLineItemRepository cartLineItemRepository;

   @Spy
   ItemRepository itemRepository;

    @BeforeEach
    void beforeEach(){
        this.cartService = new CartService(cartRepository, cartLineItemRepository, new CartMapper(), new ItemComparisonValidator(itemRepository));
    }

    @Test
    @DisplayName("장바구니에 상품 등록을 성공한다.")
    void registerCartLineItem(){

        //given
        Cart cart = CartFactory.createCart();
        Item item = ItemFactory.createMacBook();
        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));
        given(cartRepository.findByIdAndStatus(1L)).willReturn(Optional.of(cart));
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.registerMacBook().toServiceDto();

        //when
        cartService.registerCartLineItem(1L, dto);

        //then
        assertThat(cart.getCartLineItems().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("장바구니에 상품을 등록할 때 기존의 상품 옵션과 달라서 장바구니 상품을 담는 것에 실패한다.")
    void failRegisterCartLineItemCase1(){

        //given
        Cart cart = CartFactory.createCart();
        Item item = ItemFactory.createMacBook();
        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));
        given(cartRepository.findByIdAndStatus(1L)).willReturn(Optional.of(cart));
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.createFailCase1CaseMackBookDto().toServiceDto();


        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            cartService.registerCartLineItem(1L, dto);
        });

        //then
        result.isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("장바구니에 상픔을 등록할 때 상품 이름이 달라 장바구니 상품을 담는 것에 실패한다.")
    void failRegisterCartLineItemCase2(){

        //given
        Cart cart = CartFactory.createCart();
        Item item = ItemFactory.createMacBook();
        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));
        given(cartRepository.findByIdAndStatus(1L)).willReturn(Optional.of(cart));
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.createFailCase2CaseMackBookDto().toServiceDto();


        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            cartService.registerCartLineItem(1L, dto);
        });

        //then
        result.isInstanceOf(IllegalArgumentException.class);
        result.hasMessage("상품 이름이 변경되었습니다.");
    }

    @Test
    @DisplayName("장바구니에 상픔을 등록할 때 상품 옵션이 달라 장바구니 상품을 담는 것에 실패한다.")
    void failCreateOrderCase3(){

        //given
        Cart cart = CartFactory.createCart();
        Item item = ItemFactory.createMacBook();
        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));
        given(cartRepository.findByIdAndStatus(1L)).willReturn(Optional.of(cart));
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.createFailCase3CaseMackBookDto().toServiceDto();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            cartService.registerCartLineItem(1L, dto);
        });

        //then
        result.isInstanceOf(IllegalArgumentException.class);
        result.hasMessage("상품 옵션이 변경됐습니다.");
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
