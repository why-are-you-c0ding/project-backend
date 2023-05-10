package wayc.backend.common.redis;

import java.time.Duration;
import java.util.Optional;

public interface RedisService {

    <T> Optional<T> get(final String key, final Class<T> type);

    void set(final String key, final Object value, final Duration duration);

    boolean delete(final String key);

    boolean hasKey(final String key);
}
