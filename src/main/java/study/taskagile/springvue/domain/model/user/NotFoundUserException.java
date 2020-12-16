package study.taskagile.springvue.domain.model.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class NotFoundUserException  extends UsernameNotFoundException {
    private static final String MESSAGE = "not found user";

    public NotFoundUserException() {
        super(MESSAGE);
    }

}
