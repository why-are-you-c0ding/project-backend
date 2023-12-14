package wayc.backend.cart.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.cart.application.dto.response.FindCartResponseDto;
import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.repository.CartRepository;
import wayc.backend.cart.exception.NotExistsCartException;

@Service
@RequiredArgsConstructor
public class CartProvider {

    private final CartRepository cartRepository;

    //TODO 수정해야함
    @Transactional(readOnly = true)
    public FindCartResponseDto findCart(Long memberId){
        Cart cart = cartRepository.findByIdAndStatus(memberId)
                .orElseThrow(NotExistsCartException::new);
        return FindCartResponseDto.of(cart);
    }
}
