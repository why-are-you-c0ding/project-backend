package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.member.application.dto.request.AbstractRegisterMemberRequestDto;
import wayc.backend.member.application.dto.response.RegisterMemberResponseDto;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.domain.Member;
import wayc.backend.member.exception.DuplicatedLoginIdException;
import wayc.backend.member.exception.DuplicatedNickNameException;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional(readOnly = false)
    public RegisterMemberResponseDto registerMember(AbstractRegisterMemberRequestDto dto) {
        Member member = memberMapper.mapFrom(dto);
        memberRepository.save(member);
        member.registered();
        return RegisterMemberResponseDto.of(member);
    }

    public void validateLoginId(String loginId){
        if(memberRepository.findByLoginIdAndStatus(loginId).isPresent()){
            throw new DuplicatedLoginIdException();
        }
    }

    public void validateNickName(String nickName){
        if(memberRepository.findByNickNameAndStatus(nickName).isPresent()){
            throw new DuplicatedNickNameException();
        }
    }
}
