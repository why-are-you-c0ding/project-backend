package wayc.backend.common.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public <T> Optional<T> get(String key, Class<T> type) {
        String serializedValue = redisTemplate.opsForValue().get(key);
        try {
            return Optional.ofNullable(objectMapper.readValue(serializedValue, type)); //내부에서 null 검사를 다 해준다.
        } catch (Exception e) {
            throw new RuntimeException("존재하지 않는 key 값입니다.");
        }
    }

    @Override
    public void set(String key, Object value, Duration duration) {
        try{
            String serializedValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, serializedValue, duration);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 파싱 에러입니다.");
        }
    }

    @Override
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public boolean hasKey(String email) {
        return redisTemplate.hasKey(email);
    }
}
