package wayc.backend.integration.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.factory.member.dto.CreateMemberRequestDtoFactory;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.application.dto.request.CreateConsumerRequestDto;
import wayc.backend.member.application.dto.request.CreateSellerRequestDto;
import wayc.backend.member.application.dto.response.CreateMemberResponseDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberIntegrationTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("소비자 멤버 생성 성공 통합 테스트")
    void create_consumer(){
        //given
        CreateConsumerRequestDto dto = CreateMemberRequestDtoFactory.createSuccessConsumerDto();

        //when
        CreateMemberResponseDto result = memberService.createConsumer(dto);

        //then
        assertThat(result.getAge()).isEqualTo(24);
        assertThat(result.getNickName()).isEqualTo("nickName");
    }

    @Test
    @DisplayName("판매자 생성 성공 통합 테스트")
    void create_seller(){
        //given
        CreateSellerRequestDto dto = CreateMemberRequestDtoFactory.createSuccessSellerDto();

        //when
        CreateMemberResponseDto result = memberService.createSeller(dto);

        //then
        assertThat(result.getAge()).isEqualTo(24);
        assertThat(result.getNickName()).isEqualTo("nickName");
    }
}
