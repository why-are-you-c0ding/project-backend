package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.member.application.dto.request.AbstractRegisterMemberRequestDto;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.MemberValidator;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.domain.Member;
import wayc.backend.member.exception.email.NotExistsEmailException;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = false)
    public void registerMember(AbstractRegisterMemberRequestDto dto) {
        Member member = dto.toEntity(passwordEncoder, new Email(dto.getEmail(), dto.getAuthKey()));
        memberValidator.validate(member, dto.getCheckPassword(), dto.getAuthKey());
        memberRepository.save(member);
        member.registered();
    }
}
