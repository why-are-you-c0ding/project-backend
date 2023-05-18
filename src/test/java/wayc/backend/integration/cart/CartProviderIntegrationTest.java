package wayc.backend.integration.cart;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.cart.application.CartProvider;
import wayc.backend.cart.application.CartService;
import wayc.backend.cart.application.dto.request.RegisterCartLineItemRequestDto;
import wayc.backend.cart.application.dto.response.FindCartResponseDto;
import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.repository.CartRepository;
import wayc.backend.factory.cart.RegisterCartLineItemRequestFactory;
import wayc.backend.factory.member.domain.MemberFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.domain.repository.MemberRepository;

import javax.persistence.EntityManager;


public class CartProviderIntegrationTest extends IntegrationTest {

    @Autowired
    private CartProvider cartProvider;

    @Autowired
    private CartService cartService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Test
    @DisplayName("카트의 내용을 조회한다.")
    void findCart(){

        //given
        RegisterCartLineItemRequestDto dto = RegisterCartLineItemRequestFactory.createSuccessCase().toServiceDto();
        Email saveEmail = emailRepository.save(new Email("123@gmail.com", "111111"));
        Member findMember = memberRepository.save(Member.builder().email(saveEmail).build());
        cartRepository.save(new Cart(findMember.getId()));
        cartService.registerCartLineItem(findMember.getId(), dto);

        //cartService.register(findMember.getId()); 이게 Requires New라서 뭔가 문제가 있는 거 같다.
        //트랜잭션 Requires New를 도입하면서 롤백하지 않는 문제가 발생하는 것 같다. DB에 문제가 생김 나중에 관련해서 살펴보자.

        //when
        FindCartResponseDto result = cartProvider.findCart(findMember.getId());

        //then
        Assertions.assertThat(result.getCartLineItems().size()).isEqualTo(1);
    }
}
