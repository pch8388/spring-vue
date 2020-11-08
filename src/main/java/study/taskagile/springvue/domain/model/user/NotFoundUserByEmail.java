package study.taskagile.springvue.domain.model.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class NotFoundUserByEmail extends UsernameNotFoundException {
    public NotFoundUserByEmail() {
        super("Not found User by Email");
    }
}
