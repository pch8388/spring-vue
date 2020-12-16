package study.taskagile.springvue.domain.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.taskagile.springvue.infrastructure.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFinder {

    private final UserRepository userRepository;

    public User find(String usernameOrEmailAddress) {
        final Optional<User> findUser = usernameOrEmailAddress.contains("@") ?
            userRepository.findByEmailAddress(usernameOrEmailAddress) :
            userRepository.findByUsername(usernameOrEmailAddress);

        return findUser.orElseThrow(NotFoundUserException::new);
    }
}
