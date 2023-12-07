package wayc.backend.config;

import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.RedisCommand;
import org.redisson.client.protocol.RedisCommands;
import org.redisson.command.CommandAsyncService;
import org.redisson.spring.data.connection.RedissonClusterConnection;
import org.redisson.spring.data.connection.RedissonConnection;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.RedisSessionRepository;
import wayc.backend.security.oauth2.CustomOAuth2UserService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class RedisSessionConfig {

    static class CustomRedisConnection extends RedissonClusterConnection {

        CommandAsyncService executorService;


        public CustomRedisConnection(RedissonClient redisson) {
            super(redisson);
            executorService = (CommandAsyncService) this.redisson.getCommandExecutor();
        }

        @Override
        public void hMSet(byte[] key, Map<byte[], byte[]> hashes) {
            List<Object> params = new ArrayList<Object>(hashes.size()*2 + 1);
            params.add(key);
            for (Map.Entry<byte[], byte[]> entry : hashes.entrySet()) {
                params.add(entry.getKey());
                params.add(entry.getValue());
            }

            write(key, StringCodec.INSTANCE, RedisCommands.HSET, params.toArray());
        }

        <T> T write(byte[] key, Codec codec, RedisCommand<?> command, Object... params) {
            RFuture<T> f = executorService.writeAsync(key, codec, command, params);
            indexCommand(command);
            return sync(f);
        }
    }

    static class CustomRedissonConnectionFactory extends RedissonConnectionFactory {

        private RedissonClient redisson;

        public CustomRedissonConnectionFactory(RedissonClient redisson) {
            super(redisson);
            this.redisson = redisson;
        }

        @Override
        public RedisConnection getConnection() {
            if (redisson.getConfig().isClusterConfig()) {
                return new CustomRedisConnection(redisson);
            }
            return new RedissonConnection(redisson);
        }

    }

    @Bean
    public RedisConnection redisConnection(RedissonClient redissonClient) {
        return new CustomRedisConnection(redissonClient);
    }

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redissonClient){
        return new CustomRedissonConnectionFactory(redissonClient);
    }

    @Bean
    public RedisOperations<String, Object> sessionRedisOperations(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    public RedisSessionRepository sessionRepository(RedisOperations<String, Object> sessionRedisOperations) {
        RedisSessionRepository redisSessionRepository = new RedisSessionRepository(sessionRedisOperations);
        //redisSessionRepository.setDefaultMaxInactiveInterval();
        return redisSessionRepository;
    }
}
