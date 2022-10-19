package wayc.backend.unit.member.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import wayc.backend.factory.member.dto.CreateMemberRequestDtoFactory;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.application.MemberValidator;
import wayc.backend.member.application.dto.request.CreateConsumerRequestDto;
import wayc.backend.member.application.dto.request.CreateSellerRequestDto;
import wayc.backend.member.application.dto.response.CreateMemberResponseDto;
import wayc.backend.member.infrastructure.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberValidator memberValidator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("소비자 멤버 생성 성공 서비스 단위 테스트")
    void successCreateConsumerTest(){
        //given
        CreateConsumerRequestDto dto = CreateMemberRequestDtoFactory.createSuccessConsumerDto();

        //when
        CreateMemberResponseDto result = memberService.createConsumer(dto);

        //then
        assertThat(result.getAge()).isEqualTo(24);
        assertThat(result.getNickName()).isEqualTo("nickName");
    }

    @Test
    @DisplayName("판매자 멤버 생성 성공 서비스 단위 테스트")
    void successCreateSellerTest(){
        //given
        CreateSellerRequestDto dto = CreateMemberRequestDtoFactory.createSuccessSellerDto();

        //when
        CreateMemberResponseDto result = memberService.createSeller(dto);

        //then
        assertThat(result.getAge()).isEqualTo(24);
        assertThat(result.getNickName()).isEqualTo("nickName");
    }
}