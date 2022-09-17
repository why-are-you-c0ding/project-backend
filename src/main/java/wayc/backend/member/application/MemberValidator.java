package wayc.backend.member.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.member.application.dto.request.AbstractCreateMemberRequestDto;
import wayc.backend.verification.application.VerificationService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberValidator {

    private final VerificationService verificationService;

    public void validateCreateMember(AbstractCreateMemberRequestDto dto){
        verificationService.isNotSamePasswords(dto.getPassword(), dto.getCheckPassword());
        verificationService.ExistSameNickName(dto.getNickName());
        verificationService.ExistSamLoginId(dto.getLoginId());
    }
}
