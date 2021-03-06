package study.taskagile.springvue.infrastructure.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import study.taskagile.springvue.domain.model.user.User;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("저장 성공")
    @Transactional
    void save() {
        final User saveUser = userRepository.save(
            User.create("test", "test@test.com", "testPass!!"));

        assertNotNull(saveUser.getCreateDate());
        assertNotNull(saveUser.getId());
    }
}