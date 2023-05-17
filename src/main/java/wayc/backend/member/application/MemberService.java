package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.member.application.dto.request.AbstractRegisterMemberRequestDto;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.domain.Member;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional(readOnly = false)
    public void registerMember(AbstractRegisterMemberRequestDto dto) {
        Member member = memberMapper.mapFrom(dto);
        memberRepository.save(member);
        member.registered();
    }
}
