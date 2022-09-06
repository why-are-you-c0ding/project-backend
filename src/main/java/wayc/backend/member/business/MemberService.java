package wayc.backend.member.business;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.member.business.dto.request.CreateMemberRequestDto;
import wayc.backend.member.business.dto.response.CreateMemberResponseDto;
import wayc.backend.member.dataaccess.MemberRepository;
import wayc.backend.member.domain.Member;
import wayc.backend.verification.business.VerificationService;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final VerificationService verificationService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = false)
    public CreateMemberResponseDto createMember(CreateMemberRequestDto dto) {
        validateCreateMember(dto);
        Member member = dto.toEntity(passwordEncoder);
        memberRepository.save(member);
        return CreateMemberResponseDto.of(member);
    }

    public void validateCreateMember(CreateMemberRequestDto dto){
        verificationService.isNotSamePasswords(dto.getPassword(), dto.getCheckPassword());
        verificationService.ExistSameNickName(dto.getNickName());
        verificationService.ExistSamLoginId(dto.getLoginId());
    }
}
