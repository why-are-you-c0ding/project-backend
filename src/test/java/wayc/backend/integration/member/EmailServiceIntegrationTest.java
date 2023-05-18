package wayc.backend.integration.member;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.common.redis.RedisService;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.member.application.EmailService;
import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.exception.email.NotExistsEmailException;
import wayc.backend.member.exception.email.WrongEmailAuthKeyException;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

public class EmailServiceIntegrationTest extends IntegrationTest {

    @Autowired
    EmailService emailService;

    @Autowired
    RedisService redisService;

    @Autowired
    EmailRepository emailRepository;

    @Test
    void successVerifyEmail(){

        //given
        String email = "123@gmail.com";
        String authKey = "111222";
        redisService.set(email, authKey, Duration.ofSeconds(15));

        //when
        emailService.verifyEmail(email, authKey);

        //then
        assertThat(emailRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("인증을 진행하지 않은 이메일로 인증을 시도하므로 실패.")
    void failVerifyEmailCase1(){

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            emailService.verifyEmail("123466@gmail.com", "112233");
        });

        //then
        result.isInstanceOf(RuntimeException.class);
        result.hasMessage("JSON 파싱 에러입니다.");
    }

    @Test
    @DisplayName("올바르지 않은 이메일 인증 키로 인증을 시도하므로 실패.")
    void failVerifyEmailCase2(){

        //given
        String email = "12345@gmail.com";
        String authKey = "111222";
        redisService.set(email, authKey, Duration.ofSeconds(15));

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            emailService.verifyEmail(email, "112233");
        });

        //then
        result.isInstanceOf(WrongEmailAuthKeyException.class);
    }

    @Test
    @DisplayName("인증 이메일 요청 성공")
    void sendVerificationEmail(){
        String email = "asb0711@gmail.com";

        //when
        emailService.sendVerificationEmail(email);

        //then
        String result = redisService.get(email, String.class).get();
        Assertions.assertThat(result).isNotBlank();
    }

}

//redis는 롤백이 되지 않음. 나중에 클리너를 만들어 줘야한다.