package study.taskagile.springvue.domain.model.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class NotFoundUserByUsernameException extends UsernameNotFoundException {
    public NotFoundUserByUsernameException() {
        super("Not Found User by Username");
    }
}
