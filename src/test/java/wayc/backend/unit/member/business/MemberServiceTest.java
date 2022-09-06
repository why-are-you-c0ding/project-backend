package wayc.backend.unit.member.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import wayc.backend.factory.member.dto.CreateMemberRequestDtoFactory;
import wayc.backend.member.business.MemberService;
import wayc.backend.member.business.dto.request.CreateMemberRequestDto;
import wayc.backend.member.business.dto.response.CreateMemberResponseDto;
import wayc.backend.member.dataaccess.MemberRepository;
import wayc.backend.verification.business.VerificationService;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private VerificationService verificationService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("멤버 생성 성공 서비스 단위 테스트")
    void successCreateDeliveryPartyTest(){
        //given
        CreateMemberRequestDto dto = CreateMemberRequestDtoFactory.createSuccessCaseDto();

        //when
        CreateMemberResponseDto result = memberService.createMember(dto);

        //then
        assertThat(result.getAge()).isEqualTo(24);
        assertThat(result.getNickName()).isEqualTo("nickName");
    }
}