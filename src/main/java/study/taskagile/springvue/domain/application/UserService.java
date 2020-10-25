package study.taskagile.springvue.domain.application;

import study.taskagile.springvue.domain.application.command.RegistrationCommand;

public interface UserService {
    void register(RegistrationCommand command);
}
