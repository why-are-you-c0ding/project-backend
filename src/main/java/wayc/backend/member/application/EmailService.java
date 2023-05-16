package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.common.redis.RedisService;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.domain.service.SendEmailService;
import wayc.backend.member.exception.email.NotExistsEmailException;
import wayc.backend.member.exception.email.WrongEmailAuthKeyException;
import wayc.backend.member.presentation.dto.response.ValidateEmailResponse;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;
    private final SendEmailService sendEmailService;

    private final RedisService redisService;

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
        return !redisService.hasKey(receiveEmail);
    }

    private boolean wrongAuthKey(String receiveEmail, String certificationNumber) {
        return !redisService.get(receiveEmail, String.class).get().equals(certificationNumber);
    }

    public void sendVerificationEmail(String receiveEmail) {
        ValidateEmailResponse res = sendEmailService.sendVerificationEmail(receiveEmail);
        if(redisService.hasKey(res.getEmail())){
            redisService.delete(res.getEmail());
        }
        redisService.set(res.getEmail(), res.getAuthKey(), Duration.ofSeconds(5 * 60));
    }
}
