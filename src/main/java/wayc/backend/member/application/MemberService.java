package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.member.application.dto.request.CreateConsumerRequestDto;
import wayc.backend.member.application.dto.request.CreateSellerRequestDto;
import wayc.backend.member.application.dto.response.CreateMemberResponseDto;
import wayc.backend.member.infrastructure.MemberRepository;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.Email;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;
    private final PasswordEncoder passwordEncoder;

    public CreateMemberResponseDto createConsumer(CreateConsumerRequestDto dto) {
        Email email = memberValidator.validateCreateMember(dto);
        Member member = dto.toEntity(passwordEncoder, email);
        saveMember(member);
        return CreateMemberResponseDto.of(member);
    }

    public CreateMemberResponseDto createSeller(CreateSellerRequestDto dto) {
        Email email = memberValidator.validateCreateMember(dto);
        Member member = dto.toEntity(passwordEncoder, email);
        saveMember(member);
        return CreateMemberResponseDto.of(member);
    }

    @Transactional(readOnly = false)
    public void saveMember(Member member) {
        memberRepository.save(member);
    }
    //트랜잭션 락을 고려해서 분리

}
