package wayc.backend.cart.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wayc.backend.cart.application.dto.response.ShowCartResponseDto;
import wayc.backend.cart.dataaccess.CartRepository;
import wayc.backend.cart.domain.Cart;
import wayc.backend.exception.cart.NotExistsCartException;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public void create(Long memberId){
        cartRepository.save(new Cart(memberId));
    }

    public ShowCartResponseDto show(Long memberId){
        Cart cart = cartRepository.findByIdAndStatus(memberId)
                .orElseThrow(NotExistsCartException::new);
        return ShowCartResponseDto.of(cart);

    }
}
