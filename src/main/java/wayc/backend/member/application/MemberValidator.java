package wayc.backend.member.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.member.exception.NotExistsEmailException;
import wayc.backend.member.application.dto.request.AbstractCreateMemberRequestDto;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.repository.EmailRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberValidator {

    private final VerificationService verificationService;
    private final EmailRepository emailRepository;

    public Email validateCreateMember(AbstractCreateMemberRequestDto dto){
        verificationService.isNotSamePasswords(dto.getPassword(), dto.getCheckPassword());
        verificationService.ExistSameNickName(dto.getNickName());
        verificationService.ExistSamLoginId(dto.getLoginId());
        return validateEmail(dto.getEmail(), dto.getAuthKey());
    }

    public Email validateEmail(String email, String authKey){
        return emailRepository.findByIdAndAuthKey(email, authKey)
                .orElseThrow(NotExistsEmailException::new);
    }
}
