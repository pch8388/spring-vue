package study.taskagile.springvue.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import study.taskagile.springvue.domain.model.user.User;
import study.taskagile.springvue.domain.model.user.UserRepository;

public interface JpaUserRepository  extends CrudRepository<User, Long>, UserRepository {
}
