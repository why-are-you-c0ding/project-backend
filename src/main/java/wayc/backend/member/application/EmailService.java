package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.domain.service.SendEmailService;
import wayc.backend.member.exception.email.NotExistsEmailException;
import wayc.backend.member.exception.email.WrongEmailAuthKeyException;
import wayc.backend.member.infrastructure.EmailRedisRepository;
import wayc.backend.member.presentation.dto.response.ValidateEmailResponse;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;
    private final SendEmailService sendEmailService;
    private final EmailRedisRepository emailRedisRepository;

    @Transactional(readOnly = false)
    public void verifyEmail(String receiveEmail, String authKey) {

        if(notExistsEmail(receiveEmail)){
            throw new NotExistsEmailException();
        }

        if(wrongAuthKey(receiveEmail, authKey)){
            throw new WrongEmailAuthKeyException();
        }
        emailRepository.save(new Email(receiveEmail, authKey));
    }

    private boolean notExistsEmail(String receiveEmail) {
        return !emailRedisRepository.hasKey(receiveEmail);
    }

    private boolean wrongAuthKey(String receiveEmail, String certificationNumber) {
        return !emailRedisRepository.getEmailCertification(receiveEmail).equals(certificationNumber);
    }

    public void sendVerificationEmail(String receiveEmail) {
        ValidateEmailResponse res = sendEmailService.sendVerificationEmail(receiveEmail);
        emailRedisRepository.createEmailCertification(res.getEmail(), res.getAuthKey());
    }
}
