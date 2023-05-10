package wayc.backend.integration.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.factory.member.dto.RegisterMemberRequestDtoFactory;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.application.dto.request.RegisterConsumerRequestDto;
import wayc.backend.member.application.dto.request.RegisterSellerRequestDto;
import wayc.backend.member.application.dto.response.RegisterMemberResponseDto;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.repository.EmailRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberIntegrationTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private EmailRepository emailRepository;

    @Test
    @DisplayName("소비자 멤버 생성 성공 통합 테스트")
    void create_consumer(){
        //given
        emailRepository.save(new Email("123@gmail.com", "999999"));
        RegisterConsumerRequestDto dto = RegisterMemberRequestDtoFactory.createSuccessConsumerDto();

        //when
        RegisterMemberResponseDto result = memberService.registerMember(dto);

        //then
        assertThat(result.getAge()).isEqualTo(24);
        assertThat(result.getNickName()).isEqualTo("nickName");
    }

    @Test
    @DisplayName("판매자 생성 성공 통합 테스트")
    void create_seller(){
        //given
        emailRepository.save(new Email("123@gmail.com", "999999"));
        RegisterSellerRequestDto dto = RegisterMemberRequestDtoFactory.createSuccessSellerDto();

        //when
        RegisterMemberResponseDto result = memberService.registerMember(dto);

        //then
        assertThat(result.getAge()).isEqualTo(24);
        assertThat(result.getNickName()).isEqualTo("nickName");
    }
}
