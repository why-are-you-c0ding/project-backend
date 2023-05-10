package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.member.application.dto.request.AbstractRegisterMemberRequestDto;
import wayc.backend.member.application.dto.response.RegisterMemberResponseDto;
import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.service.SendEmailService;
import wayc.backend.member.exception.DuplicatedLoginIdException;
import wayc.backend.member.exception.DuplicatedNickNameException;
import wayc.backend.member.exception.email.NotExistsEmailException;
import wayc.backend.member.exception.email.WrongEmailAuthKeyException;
import wayc.backend.member.infrastructure.EmailRedisRepository;
import wayc.backend.member.presentation.dto.response.ValidateEmailResponse;

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
