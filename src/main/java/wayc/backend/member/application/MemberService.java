package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.member.application.dto.request.AbstractCreateMemberRequestDto;
import wayc.backend.member.application.dto.request.CreateConsumerRequestDto;
import wayc.backend.member.application.dto.request.CreateSellerRequestDto;
import wayc.backend.member.application.dto.response.CreateMemberResponseDto;
import wayc.backend.member.dataaccess.MemberRepository;
import wayc.backend.member.domain.Member;
import wayc.backend.verification.application.VerificationService;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final VerificationService verificationService;
    private final PasswordEncoder passwordEncoder;

    public CreateMemberResponseDto createConsumer(CreateConsumerRequestDto dto) {
        validateCreateMember(dto);
        Member member = dto.toEntity(passwordEncoder);
        saveMember(member);
        return CreateMemberResponseDto.of(member);
    }

    public CreateMemberResponseDto createSeller(CreateSellerRequestDto dto) {
        validateCreateMember(dto);
        Member member = dto.toEntity(passwordEncoder);
        saveMember(member);
        return CreateMemberResponseDto.of(member);
    }

    @Transactional(readOnly = false)
    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public void validateCreateMember(AbstractCreateMemberRequestDto dto){
        verificationService.isNotSamePasswords(dto.getPassword(), dto.getCheckPassword());
        verificationService.ExistSameNickName(dto.getNickName());
        verificationService.ExistSamLoginId(dto.getLoginId());
    }
}
