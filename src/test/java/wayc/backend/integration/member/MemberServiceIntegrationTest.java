package wayc.backend.integration.member;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wayc.backend.common.redis.RedisService;
import wayc.backend.factory.member.domain.MemberFactory;
import wayc.backend.factory.member.dto.RegisterMemberRequestDtoFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.application.dto.request.RegisterConsumerRequestDto;
import wayc.backend.member.application.dto.request.RegisterSellerRequestDto;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.exception.DuplicatedLoginIdException;
import wayc.backend.member.exception.DuplicatedNickNameException;
import wayc.backend.member.exception.NotSamePasswordException;
import wayc.backend.member.exception.email.NotExistsEmailException;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static wayc.backend.factory.member.dto.RegisterMemberRequestDtoFactory.*;

public class MemberServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RedisService redisService;

    @Test
    @DisplayName("소비자 멤버 생성 성공 통합 테스트")
    void create_consumer(){
        //given
        RegisterConsumerRequestDto dto = RegisterMemberRequestDtoFactory.createSuccessConsumerDto();
        redisService.set(dto.getEmail(), dto.getAuthKey(), Duration.ofSeconds(10));


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
        RegisterSellerRequestDto dto = RegisterMemberRequestDtoFactory.createSuccessSellerDto();
        redisService.set(dto.getEmail(), dto.getAuthKey(), Duration.ofSeconds(10));

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
        redisService.set(dto.getEmail(), dto.getAuthKey(), Duration.ofSeconds(10));
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
        redisService.set(dto.getEmail(), dto.getAuthKey(), Duration.ofSeconds(10));
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
        redisService.set(dto.getEmail(), dto.getAuthKey(), Duration.ofSeconds(10));


        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberService.registerMember(dto);
        });

        //then
        result.isInstanceOf(NotSamePasswordException.class);
    }

    @Test
    @DisplayName("중복되는 로그인 아이디가 존재하므로 예외 발생")
    void validateLoginIdTest(){
        //given
        Member member = MemberFactory.createMemberWhenValidateLoginId();
        memberRepository.save(member);

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberService.validateLoginId(member.getLoginId());
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
            memberService.validateNickName(member.getNickName());
        });

        //then
        result.isInstanceOf(DuplicatedNickNameException.class);
    }

    @Test
    @DisplayName("중복되는 로그인 아이디가 존재하므로 예외 발생")
    void passValidateLoginIdTest(){
        memberService.validateLoginId("ex");
    }

    @Test
    @DisplayName("중복되는 닉네임이 존재하므로 예외 발생")
    void passValidateNickNameTest(){
        memberService.validateNickName("ex");
    }
}
