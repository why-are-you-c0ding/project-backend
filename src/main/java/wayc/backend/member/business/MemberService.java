package wayc.backend.member.business;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.member.business.dto.request.CreateMemberRequestDto;
import wayc.backend.member.business.dto.response.CreateMemberResponseDto;
import wayc.backend.member.dataaccess.MemberRepository;
import wayc.backend.member.domain.Member;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public CreateMemberResponseDto createMember(CreateMemberRequestDto dto) {

        if(!dto.getPassword().equals(dto.getCheckPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다");
        }

        if(memberRepository.findByNickName(dto.getNickName()).isPresent()){
            throw new IllegalStateException("이미 존재하는 닉네임입니다");
        }

        if(memberRepository.findByLoginId(dto.getLoginId()).isPresent()){
            throw new IllegalStateException("이미 존재하는 로그인 아이디입니다");
        }

        Member member = dto.toEntity();
        Member saveMember = memberRepository.save(member);
        return CreateMemberResponseDto.of(saveMember);
    }
}
