package study.taskagile.springvue.domain.model.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class NotFoundUserByUsername extends UsernameNotFoundException {
    public NotFoundUserByUsername() {
        super("Not Found User by Username");
    }
}
