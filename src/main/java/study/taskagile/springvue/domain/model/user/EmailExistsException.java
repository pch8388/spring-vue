package study.taskagile.springvue.domain.model.user;

public class EmailExistsException extends RegistrationException {
    public EmailExistsException() {
        super("Email already exists");
    }
}
