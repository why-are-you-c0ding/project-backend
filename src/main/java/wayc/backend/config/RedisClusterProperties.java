package wayc.backend.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Getter
@Setter //이걸 달아야지 동작한다.
public class RedisClusterProperties {

    List<String> nodes;
}
