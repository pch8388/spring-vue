package study.taskagile.springvue.domain.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import study.taskagile.springvue.domain.application.command.RegistrationCommand;
import study.taskagile.springvue.domain.model.user.User;

public interface UserService extends UserDetailsService {
    User register(RegistrationCommand command);
}
