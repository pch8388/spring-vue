package study.taskagile.springvue.domain.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.taskagile.springvue.domain.common.security.PasswordEncryptor;
import study.taskagile.springvue.infrastructure.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class RegistrationManagement {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    public User register(String username, String emailAddress, String password) {
        if (isExistUserFindByUsername(username)) {
            throw new UsernameExistsException();
        }

        if (isExistUserFindByEmailAddress(emailAddress)) {
            throw new EmailExistsException();
        }

        String encryptedPassword = passwordEncryptor.encrypt(password);
        return userRepository.save(
            User.create(username, emailAddress, encryptedPassword));
    }

    private boolean isExistUserFindByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress).isPresent();
    }

    private boolean isExistUserFindByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
