package wayc.backend.unit.application.member;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import wayc.backend.factory.member.domain.MemberFactory;
import wayc.backend.member.application.MemberMapper;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.MemberValidator;
import wayc.backend.member.application.dto.request.RegisterConsumerRequestDto;
import wayc.backend.member.application.dto.request.RegisterSellerRequestDto;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.exception.DuplicatedLoginIdException;
import wayc.backend.member.exception.DuplicatedNickNameException;
import wayc.backend.member.exception.NotSamePasswordException;
import wayc.backend.member.exception.email.NotExistsEmailException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static wayc.backend.factory.member.dto.RegisterMemberRequestDtoFactory.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailRepository emailRepository;

    @BeforeEach
    void beforeEach(){
        memberService = new MemberService(memberRepository, new MemberMapper(new MemberValidator(memberRepository), passwordEncoder, emailRepository));
    }

    @Test
    @DisplayName("소비자 멤버 생성 성공 서비스 단위 테스트")
    void successCreateConsumerTest(){
        //given
        RegisterConsumerRequestDto dto = createSuccessConsumerDto();
        given(memberRepository.findByNickNameAndStatus(any(String.class)))
                .willReturn(Optional.ofNullable(null));
        given(memberRepository.findByLoginIdAndStatus(any(String.class)))
                .willReturn(Optional.ofNullable(null));
        given(emailRepository.findByIdAndAuthKey(any(String.class), any(String.class)))
                .willReturn(Optional.of(new Email(dto.getEmail(), dto.getAuthKey())));

        //when
        memberService.registerMember(dto);

        //then
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    @DisplayName("판매자 멤버 생성 성공 서비스 단위 테스트")
    void successCreateSellerTest(){
        //given
        RegisterSellerRequestDto dto = createSuccessSellerDto();
        given(memberRepository.findByNickNameAndStatus(any(String.class)))
                .willReturn(Optional.ofNullable(null));
        given(memberRepository.findByLoginIdAndStatus(any(String.class)))
                .willReturn(Optional.ofNullable(null));
        given(emailRepository.findByIdAndAuthKey(any(String.class), any(String.class)))
                .willReturn(Optional.of(new Email(dto.getEmail(), dto.getAuthKey())));

        //when
        memberService.registerMember(dto);

        //then
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    @DisplayName("중복되는 이름이 존재하므로 멤버 생성에 실패한다.")
    void failRegisterMemberCase1(){
        //given
        RegisterConsumerRequestDto dto = createSuccessConsumerDto();
        given(memberRepository.findByNickNameAndStatus(any(String.class)))
                .willReturn(Optional.ofNullable(MemberFactory.createConsumerSuccessCase()));

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberService.registerMember(dto);
        });

        //then
        result.isInstanceOf(DuplicatedNickNameException.class);
    }

    @Test
    @DisplayName("중복되는 로그인 아이디가 존재하므로 멤버 생성에 실패한다.")
    void failRegisterMemberCase2(){
        //given
        RegisterSellerRequestDto dto = createSuccessSellerDto();
        given(memberRepository.findByNickNameAndStatus(any(String.class)))
                .willReturn(Optional.ofNullable(null));
        given(memberRepository.findByLoginIdAndStatus(any(String.class)))
                .willReturn(Optional.ofNullable(MemberFactory.createConsumerSuccessCase()));

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
        given(memberRepository.findByNickNameAndStatus(any(String.class)))
                .willReturn(Optional.ofNullable(null));
        given(memberRepository.findByLoginIdAndStatus(any(String.class)))
                .willReturn(Optional.ofNullable(null));
        given(emailRepository.findByIdAndAuthKey(any(String.class), any(String.class)))
                .willReturn(Optional.ofNullable(null));


        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            memberService.registerMember(dto);
        });

        //then
        result.isInstanceOf(NotExistsEmailException.class);
    }

    @Test
    @DisplayName("검증 비밀번호와 비밀번호가 다르므로 멤버 생성에 실패한다..")
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