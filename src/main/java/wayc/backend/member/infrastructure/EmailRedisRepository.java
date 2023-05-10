package wayc.backend.member.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class EmailRedisRepository {

    private final String PREFIX = "email:";
    private final int LIMIT_TIME = 5 * 600; //유효시간 설정

    private final StringRedisTemplate stringRedisTemplate;

    //SMS 인증 정보 저장
    public void createEmailCertification(String email, String certificationNumber) {
        stringRedisTemplate.opsForValue()
                .set(PREFIX + email, certificationNumber, Duration.ofSeconds(LIMIT_TIME));
    }

    //Redis에 있는 값을 리턴
    public String getEmailCertification(String email) {
        return stringRedisTemplate.opsForValue().get(PREFIX + email);
    }

    //제거
    public void removeEmailCertification(String email) {
        stringRedisTemplate.delete(PREFIX + email);
    }

    //키에 대한 값을 가지고 있는가
    public boolean hasKey(String email) {
        return stringRedisTemplate.hasKey(PREFIX + email);
    }
}
