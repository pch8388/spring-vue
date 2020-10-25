package study.taskagile.springvue.domain.model.user;

public class UsernameExistsException extends RegistrationException {
    public UsernameExistsException() {
        super("Username already exists");
    }
}
