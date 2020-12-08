package study.taskagile.springvue.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import study.taskagile.springvue.domain.model.user.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailAddress(String emailAddress);

    Optional<User> findByUsername(String username);
}
