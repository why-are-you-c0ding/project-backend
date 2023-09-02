package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import wayc.backend.common.redis.RedisService;

import wayc.backend.member.domain.service.SendEmailService;
import wayc.backend.member.domain.service.ValidateEmailResponseDto;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final SendEmailService sendEmailService;
    private final RedisService redisService;

    public void sendVerificationEmail(String receiveEmail) {
        ValidateEmailResponseDto res = sendEmailService.sendVerificationEmail(receiveEmail);
        if(redisService.hasKey(res.getEmail())){
            redisService.delete(res.getEmail());
        }
        redisService.set(res.getEmail(), res.getAuthKey(), Duration.ofSeconds(5 * 60));
    }
}
