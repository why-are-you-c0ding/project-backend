package wayc.backend.integration.cart;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.cart.application.CartProvider;
import wayc.backend.factory.member.domain.MemberFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.member.application.MemberProvider;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.exception.DuplicatedLoginIdException;
import wayc.backend.member.exception.DuplicatedNickNameException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CartProviderIntegrationTest extends IntegrationTest {

    @Autowired
    private CartProvider memberProvider;


    @Test
    @DisplayName("카트의 내용을 조회한다.")
    void findCart(){
        //given

        //when

        //then
    }
}
