package study.taskagile.springvue.domain.application.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.taskagile.springvue.domain.application.command.RegistrationCommand;
import study.taskagile.springvue.domain.common.event.DomainEventPublisher;
import study.taskagile.springvue.domain.common.mail.MailManager;
import study.taskagile.springvue.domain.common.mail.MessageVariable;
import study.taskagile.springvue.domain.model.user.EmailExistsException;
import study.taskagile.springvue.domain.model.user.RegistrationManagement;
import study.taskagile.springvue.domain.model.user.User;
import study.taskagile.springvue.domain.model.user.UsernameExistsException;
import study.taskagile.springvue.domain.model.user.event.UserRegisteredEvent;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class UserServiceImplTests {
    @Mock
    private RegistrationManagement registrationManagement;
    @Mock
    private DomainEventPublisher eventPublisher;
    @Mock
    private MailManager mailManager;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("register 매개변수가 null 이면 예외 발생")
    void register_nullCommand() {
        assertThrows(IllegalArgumentException.class,
            () -> userService.register(null));
    }

    @Test
    @DisplayName("사용자 이름이 중복되면 예외발생")
    void register_existUsername() {
        when(registrationManagement.register(anyString(), anyString(), anyString()))
            .thenThrow(UsernameExistsException.class);

        assertThrows(UsernameExistsException.class,
            () -> userService.register(generatedCommand()));
    }

    @Test
    @DisplayName("사용자 이메일이 중복되면 예외발생")
    void register_existEmail() {
        when(registrationManagement.register(anyString(), anyString(), anyString()))
            .thenThrow(EmailExistsException.class);

        assertThrows(EmailExistsException.class,
            () -> userService.register(generatedCommand()));
    }

    @Test
    @DisplayName("등록 성공")
    void register_success() {
        RegistrationCommand command = generatedCommand();
        User newUser = User.create(
            command.getUsername(), command.getEmailAddress(),
            command.getPassword());

        when(registrationManagement.register(anyString(), anyString(), anyString()))
            .thenReturn(newUser);

        userService.register(command);

        verify(mailManager).send(
            command.getEmailAddress(),
            "Welcome to TaskAgile",
            "welcome.ftl",
            MessageVariable.from("user", newUser)
        );
        verify(eventPublisher).publish(any());
    }

    private RegistrationCommand generatedCommand() {
        String username = "existing";
        String emailAddress = "test@test.com";
        String password = "MyPassword!";
        return new RegistrationCommand(username, emailAddress, password);
    }
}