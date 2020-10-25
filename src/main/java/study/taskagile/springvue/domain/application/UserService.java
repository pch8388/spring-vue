package study.taskagile.springvue.domain.application;

import study.taskagile.springvue.domain.application.command.RegistrationCommand;
import study.taskagile.springvue.domain.model.user.User;

public interface UserService {
    User register(RegistrationCommand command);
}
