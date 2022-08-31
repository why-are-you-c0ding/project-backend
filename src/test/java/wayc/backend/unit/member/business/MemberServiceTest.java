package wayc.backend.unit.member.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wayc.backend.factory.member.domain.MemberFactory;
import wayc.backend.member.business.MemberService;
import wayc.backend.member.dataaccess.MemberRepository;
import wayc.backend.member.domain.Member;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("배달 파티 생성 성공 테스트")
    void successCreateDeliveryPartyTest(){
        //given
        Member member = MemberFactory.createSuccessCase();

        given(memberRepository.save(member)).willReturn(member);

        //when
//        memberService.createMember()

        //then

    }
}