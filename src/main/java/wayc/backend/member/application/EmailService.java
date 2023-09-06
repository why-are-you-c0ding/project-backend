package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import wayc.backend.common.redis.RedisService;

import wayc.backend.member.domain.service.SendEmailService;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final SendEmailService sendEmailService;
    private final RedisService redisService;

    public void sendVerificationEmail(String receiveEmail, String authKey) {
        sendEmailService.sendVerificationEmail(receiveEmail, authKey);
        if(redisService.hasKey(receiveEmail)){
            redisService.delete(receiveEmail);
        }
        redisService.set(receiveEmail, authKey, Duration.ofSeconds(5 * 60));
    }
}
