package wayc.backend.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wayc.backend.common.event.Events;

@Configuration
public class EventsConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public InitializingBean eventsInitializer(){
        return () -> Events.setPublisher(applicationContext);
    }
}
