package study.taskagile.springvue.domain.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import study.taskagile.springvue.domain.application.UserService;
import study.taskagile.springvue.domain.application.command.RegistrationCommand;
import study.taskagile.springvue.domain.common.event.DomainEventPublisher;
import study.taskagile.springvue.domain.common.mail.MailManager;
import study.taskagile.springvue.domain.common.mail.MessageVariable;
import study.taskagile.springvue.domain.model.user.RegistrationManagement;
import study.taskagile.springvue.domain.model.user.User;
import study.taskagile.springvue.domain.model.user.event.UserRegisteredEvent;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RegistrationManagement registrationManagement;
    private final DomainEventPublisher domainEventPublisher;
    private final MailManager mailManager;

    @Override
    public User register(RegistrationCommand command) {
        Assert.notNull(command, "Parameter command must not be null");

        final User registerUser = registrationManagement.register(
            command.getUsername(), command.getEmailAddress(),
            command.getPassword());

        sendWelcomeMessage(registerUser);
        domainEventPublisher.publish(new UserRegisteredEvent(registerUser));
        return registerUser;
    }

    private void sendWelcomeMessage(User user) {
        mailManager.send(
            user.getEmailAddress(),
            "Welcome to TaskAgile",
            "welcome.ftl",
            MessageVariable.from("user", user));
    }
}
