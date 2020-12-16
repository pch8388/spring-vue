package study.taskagile.springvue.domain.model.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class NotFoundUserByEmailException extends UsernameNotFoundException {
    public NotFoundUserByEmailException() {
        super("Not found User by Email");
    }
}
