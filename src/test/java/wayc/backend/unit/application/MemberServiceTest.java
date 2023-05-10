package wayc.backend.unit.application;

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
import wayc.backend.member.application.dto.response.RegisterMemberResponseDto;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.domain.repository.MemberRepository;

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
        RegisterMemberResponseDto result = memberService.registerMember(dto);

        //then
        verify(memberRepository, times(1)).save(any(Member.class));
        assertThat(result.getAge()).isEqualTo(24);
        assertThat(result.getNickName()).isEqualTo("nickName");
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
        RegisterMemberResponseDto result = memberService.registerMember(dto);

        //then
        assertThat(result.getAge()).isEqualTo(24);
        assertThat(result.getNickName()).isEqualTo("nickName");
    }
}