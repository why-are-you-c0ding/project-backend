package wayc.backend.cart.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import wayc.backend.cart.application.dto.request.CreateCartLineItemRequestDto;
import wayc.backend.cart.application.dto.response.ShowCartResponseDto;

import wayc.backend.cart.domain.repository.CartLineItemRepository;
import wayc.backend.cart.domain.repository.CartRepository;

import wayc.backend.cart.domain.Cart;

import wayc.backend.cart.domain.CartLineItem;
import wayc.backend.exception.cart.NotExistsCartException;
import wayc.backend.exception.cart.NotExistsCartLineException;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartLineItemRepository cartLineItemRepository;
    private final CartMapper cartMapper;

    public void create(Long memberId){
        cartRepository.save(new Cart(memberId));
    }

    public ShowCartResponseDto show(Long memberId){
        Cart cart = cartRepository.findByIdAndStatus(memberId)
                .orElseThrow(NotExistsCartException::new);
        return ShowCartResponseDto.of(cart);
    }

    @Transactional(readOnly = false)
    public void createCartLineItem(Long memberId, CreateCartLineItemRequestDto dto){
        Cart cart = cartRepository.findByIdAndStatus(memberId)
                .orElseThrow(NotExistsCartException::new);
        CartLineItem lineItem = cartMapper.toLineItem(dto, cart);
        cart.addCartLineItem(lineItem);
    }


    @Transactional(readOnly = false)
    public void updateCartLineItem(Long memberId, Long cartLineItemId, Integer count) {
        Cart cart = cartRepository.findByIdAndStatus(memberId)
                .orElseThrow(NotExistsCartException::new);
        CartLineItem cartLineItem = cartLineItemRepository.findByIdAndStatus(cartLineItemId)
                .orElseThrow(NotExistsCartLineException::new);

        cart.update(cartLineItem, count);
    }

    @Transactional(readOnly = false)
    public void deleteCartLineItem(Long memberId, Long cartLineItemId){
        Cart cart = cartRepository.findByIdAndStatus(memberId)
                .orElseThrow(NotExistsCartException::new);
        CartLineItem cartLineItem = cartLineItemRepository.findByIdAndStatus(cartLineItemId)
                .orElseThrow(NotExistsCartLineException::new);
        cart.deleteCartLineItem(cartLineItem);
    }
}
