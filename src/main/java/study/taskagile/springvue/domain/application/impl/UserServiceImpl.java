package study.taskagile.springvue.domain.application.impl;

import org.springframework.stereotype.Service;
import study.taskagile.springvue.domain.application.UserService;
import study.taskagile.springvue.domain.application.command.RegistrationCommand;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Override
    public void register(RegistrationCommand command) {

    }
}
