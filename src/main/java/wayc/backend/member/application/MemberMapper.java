package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import wayc.backend.member.application.dto.request.AbstractRegisterMemberRequestDto;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.MemberValidator;
import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.exception.email.NotExistsEmailException;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final MemberValidator memberValidator;
    private final PasswordEncoder passwordEncoder;
    private final EmailRepository emailRepository;

    public Member mapFrom(AbstractRegisterMemberRequestDto dto){
        memberValidator.validateCreateMember(dto);
        Email email = findEmail(dto);
        return dto.toEntity(passwordEncoder, email);
    }

    private Email findEmail(AbstractRegisterMemberRequestDto dto) {
        return emailRepository.findByIdAndAuthKey(dto.getEmail(), dto.getAuthKey())
                .orElseThrow(NotExistsEmailException::new);
    }
}
