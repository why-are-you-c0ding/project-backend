package wayc.backend.cart.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import wayc.backend.cart.application.dto.request.CreateCartLineItemRequestDto;
import wayc.backend.cart.application.dto.response.ShowCartResponseDto;

import wayc.backend.cart.dataaccess.CartRepository;

import wayc.backend.cart.domain.Cart;

import wayc.backend.cart.domain.CartLineItem;
import wayc.backend.exception.cart.NotExistsCartException;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
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
}
