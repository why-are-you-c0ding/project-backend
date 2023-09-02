package wayc.backend.integration.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.common.redis.RedisService;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.member.application.EmailService;

public class EmailServiceIntegrationTest extends IntegrationTest {

    @Autowired
    EmailService emailService;

    @Autowired
    RedisService redisService;

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