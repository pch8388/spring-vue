package study.taskagile.springvue.domain.model.user.event;

import lombok.Getter;
import org.springframework.util.Assert;
import study.taskagile.springvue.domain.common.event.DomainEvent;
import study.taskagile.springvue.domain.model.user.User;

@Getter
public class UserRegisteredEvent extends DomainEvent {
    private final User user;

    public UserRegisteredEvent(User user) {
        super(user);
        Assert.notNull(user, "Parameter user must not be null");
        this.user = user;
    }
}
