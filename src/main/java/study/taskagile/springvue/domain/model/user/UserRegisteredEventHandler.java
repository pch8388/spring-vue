package study.taskagile.springvue.domain.model.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import study.taskagile.springvue.domain.model.user.event.UserRegisteredEvent;

@Component
@Slf4j
public class UserRegisteredEventHandler {
    @EventListener(UserRegisteredEvent.class)
    public void handleEvent(UserRegisteredEvent event) {
        log.debug("Handling {} registration event",
            event.getUser().getEmailAddress());
    }
}
