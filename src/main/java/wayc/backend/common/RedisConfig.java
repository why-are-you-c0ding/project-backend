package wayc.backend.common;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisClusterProperties redisClusterProperties;

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //redisTemplate.setEnableTransactionSupport(true); //트랜잭션을 걸 때는 여기가 제일 중요. -> 클러스터링을 걸면 MULTI 커맨드를 적용할 수 없음.
        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration config = new RedisClusterConfiguration(redisClusterProperties.getNodes());
        config.setPassword(RedisPassword.of("4648"));
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(config);
        return lettuceConnectionFactory;
    }

    //트랜잭션 매니저와 데이터 소스는 스프링 부트가 다 자동 설정해준다.

}



/**
 * https://docs.spring.io/spring-data/redis/docs/current/reference/html/#tx.spring
 */