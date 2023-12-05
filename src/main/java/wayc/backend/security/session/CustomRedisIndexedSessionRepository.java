package wayc.backend.security.session;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.session.SaveMode;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
public class CustomRedisIndexedSessionRepository extends RedisIndexedSessionRepository {

    public CustomRedisIndexedSessionRepository(RedisOperations<Object, Object> sessionRedisOperations) {
        super(sessionRedisOperations);
    }

    @Override
    public void cleanupExpiredSessions() {

    }

    /**
     *  이 모드에서는 세션에 대한 변경이 발생할 때만 해당 변경 사항을 저장합니다.
         *  예를 들어, Session.setAttribute(String, Object)를 사용하여 세션 속성을 설정할 때만 변경 사항이 저장됩니다.
         *  이 모드는 높은 동시성 환경에서 여러 요청이 동시에 처리될 때 속성이 덮어쓰여지는 위험을 최소화합니다.
         * @param saveMode
         */
    @Override
    public void setSaveMode(SaveMode saveMode) {
        super.setSaveMode(SaveMode.ON_SET_ATTRIBUTE);
    }

    @Override
    public void deleteById(String sessionId) {
        super.deleteById(sessionId);
    }


}
