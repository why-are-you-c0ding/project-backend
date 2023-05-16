package wayc.backend.integration.member;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.factory.member.domain.MemberFactory;
import wayc.backend.factory.member.dto.RegisterMemberRequestDtoFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.member.application.MemberProvider;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.application.dto.request.RegisterConsumerRequestDto;
import wayc.backend.member.application.dto.request.RegisterSellerRequestDto;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.exception.DuplicatedLoginIdException;
import wayc.backend.member.exception.DuplicatedNickNameException;
import wayc.backend.member.exception.NotSamePasswordException;
import wayc.backend.member.exception.email.NotExistsEmailException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static wayc.backend.factory.member.dto.RegisterMemberRequestDtoFactory.createFailMemberDto;
import static wayc.backend.factory.member.dto.RegisterMemberRequestDtoFactory.createSuccessSellerDto;

public class MemberProviderIntegrationTest extends IntegrationTest {

    @Autowired
    private MemberProvider memberProvider;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("중복되는 로그인 아이디가 존재하므로 예외 발생")
    void validateLoginIdTest(){
        //given
        Member member = MemberFactory.createMemberWhenValidateLoginId();
        memberRepository.save(member);

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberProvider.validateLoginId(member.getLoginId());
        });

        //then
        result.isInstanceOf(DuplicatedLoginIdException.class);
    }

    @Test
    @DisplayName("중복되는 닉네임이 존재하므로 예외 발생")
    void validateNickNameTest(){
        //given
        Member member = MemberFactory.createMemberWhenValidateNickName();
        memberRepository.save(member);

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberProvider.validateNickName(member.getNickName());
        });

        //then
        result.isInstanceOf(DuplicatedNickNameException.class);
    }

    @Test
    @DisplayName("중복되는 로그인 아이디가 존재하므로 예외 발생")
    void passValidateLoginIdTest(){
        memberProvider.validateLoginId("ex");
    }

    @Test
    @DisplayName("중복되는 닉네임이 존재하므로 예외 발생")
    void passValidateNickNameTest(){
        memberProvider.validateNickName("ex");
    }
}
