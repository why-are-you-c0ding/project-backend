package wayc.backend.integration.member;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wayc.backend.factory.member.domain.MemberFactory;
import wayc.backend.factory.member.dto.RegisterMemberRequestDtoFactory;
import wayc.backend.integration.IntegrationTest;
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
import static wayc.backend.factory.member.dto.RegisterMemberRequestDtoFactory.*;

public class MemberIntegrationTest extends IntegrationTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("소비자 멤버 생성 성공 통합 테스트")
    void create_consumer(){
        //given
        emailRepository.save(new Email("123@gmail.com", "999999"));
        RegisterConsumerRequestDto dto = RegisterMemberRequestDtoFactory.createSuccessConsumerDto();

        //when
        memberService.registerMember(dto);

        //then
        List<Member> result = memberRepository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("판매자 생성 성공 통합 테스트")
    void create_seller(){
        //given
        emailRepository.save(new Email("123@gmail.com", "999999"));
        RegisterSellerRequestDto dto = RegisterMemberRequestDtoFactory.createSuccessSellerDto();


        //when
        memberService.registerMember(dto);

        //then
        List<Member> result = memberRepository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("중복되는 이름이 존재하므로 멤버 생성에 실패한다.")
    void failRegisterMemberCase1(){
        //given
        RegisterSellerRequestDto dto = createSuccessSellerDto();
        Member member = MemberFactory.createMemberWhenValidateNickName();
        memberRepository.save(member);

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberService.registerMember(dto);
        });

        //then
        result.isInstanceOf(DuplicatedNickNameException.class);
    }

    @Test
    @DisplayName("동일한 로그인 아이디가 존재하므로 멤버 생성에 실패한다.")
    void failRegisterMemberCase2(){
        //given
        RegisterSellerRequestDto dto = createSuccessSellerDto();
        Member member = MemberFactory.createMemberWhenValidateLoginId();
        memberRepository.save(member);

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberService.registerMember(dto);
        });

        //then
        result.isInstanceOf(DuplicatedLoginIdException.class);
    }

    @Test
    @DisplayName("이메일이 존재하지 않아서 멤버 생성을 실패한다.")
    void failRegisterMemberCase3(){
        //given
        RegisterSellerRequestDto dto = createSuccessSellerDto();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberService.registerMember(dto);
        });

        //then
        result.isInstanceOf(NotExistsEmailException.class);
    }

    @Test
    @DisplayName("검증 비밀번호와 비밀번호가 다르므로 멤버 생성에 실패한다.")
    void failRegisterMemberCase4(){

        //given
        RegisterSellerRequestDto dto = createFailMemberDto();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberService.registerMember(dto);
        });

        //then
        result.isInstanceOf(NotSamePasswordException.class);
    }
}
