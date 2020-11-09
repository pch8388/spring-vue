package study.taskagile.springvue.domain.model.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmailAddress(String emailAddress);

    User save(User user);
}
