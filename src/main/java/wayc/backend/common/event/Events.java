package wayc.backend.common.event;

import org.springframework.context.ApplicationEventPublisher;

public class Events {

    private static ApplicationEventPublisher eventPublisher;

    public static void setPublisher(ApplicationEventPublisher publisher){
        Events.eventPublisher = publisher;
    }

    public static void raise(Event event){
        if(eventPublisher != null){
            eventPublisher.publishEvent(event);
        }
    }
}
