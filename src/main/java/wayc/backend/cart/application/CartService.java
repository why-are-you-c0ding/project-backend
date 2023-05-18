package wayc.backend.cart.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.cart.application.dto.request.RegisterCartLineItemRequestDto;
import wayc.backend.cart.domain.repository.CartLineItemRepository;
import wayc.backend.cart.domain.repository.CartRepository;
import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.CartLineItem;
import wayc.backend.cart.exception.NotExistsCartException;
import wayc.backend.cart.exception.NotExistsCartLineException;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartLineItemRepository cartLineItemRepository;
    private final CartMapper cartMapper;

    @Transactional(readOnly = false)
    public void register(Long memberId){
        cartRepository.save(new Cart(memberId));
    }

    public void registerCartLineItem(Long memberId, RegisterCartLineItemRequestDto dto){
        Cart cart = cartRepository.findByIdAndStatus(memberId)
                .orElseThrow(NotExistsCartException::new);
        CartLineItem lineItem = cartMapper.toLineItem(dto, cart);
        cart.addCartLineItem(lineItem);
    }

    public void updateCartLineItem(Long memberId, Long cartLineItemId, Integer count) {
        Cart cart = cartRepository.findByIdAndStatus(memberId)
                .orElseThrow(NotExistsCartException::new);
        CartLineItem cartLineItem = cartLineItemRepository.findByIdAndStatus(cartLineItemId)
                .orElseThrow(NotExistsCartLineException::new);

        cart.update(cartLineItem, count);
    }

    public void deleteCartLineItem(Long memberId, Long cartLineItemId){
        Cart cart = cartRepository.findByIdAndStatus(memberId)
                .orElseThrow(NotExistsCartException::new);
        CartLineItem cartLineItem = cartLineItemRepository.findByIdAndStatus(cartLineItemId)
                .orElseThrow(NotExistsCartLineException::new);
        cart.deleteCartLineItem(cartLineItem);
    }
}
