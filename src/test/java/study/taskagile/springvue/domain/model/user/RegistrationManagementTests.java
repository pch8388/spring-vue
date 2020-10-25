package study.taskagile.springvue.domain.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.taskagile.springvue.domain.common.security.PasswordEncryptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class RegistrationManagementTests {

    @Mock private UserRepository repository;
    @Mock private PasswordEncryptor passwordEncryptor;
    @InjectMocks private RegistrationManagement registrationManagement;

    private final String username = "testUser";
    private final String emailAddress = "test@google.com";
    private final String password = "MyPassword!";

    @Test
    @DisplayName("username 중복시 예외발생")
    void register_existUsername() {
        when(repository.findByUsername(username))
            .thenReturn(Optional.of(User.create(username, emailAddress, password)));

        assertThrows(UsernameExistsException.class,
            () -> registrationManagement.register(username, emailAddress, password));
    }

    @Test
    @DisplayName("email 중복시 예외발생")
    void register_existEmail() {
        when(repository.findByEmailAddress(emailAddress))
            .thenReturn(Optional.of(User.create(username, emailAddress, password)));

        assertThrows(EmailExistsException.class,
            () -> registrationManagement.register(username, emailAddress, password));
    }

    @Test
    @DisplayName("등록 성공")
    void register() {
        String encryptedPassword = "EncryptedPassword";
        final User newUser = User.create(username, emailAddress, encryptedPassword);

        when(repository.findByUsername(username)).thenReturn(Optional.empty());
        when(repository.findByEmailAddress(emailAddress)).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(newUser);
        when(passwordEncryptor.encrypt(password)).thenReturn("EncryptedPassword");

        final User saveUser = registrationManagement.register(username, emailAddress, password);
        InOrder inOrder = inOrder(repository);
        inOrder.verify(repository).findByUsername(username);
        inOrder.verify(repository).findByEmailAddress(emailAddress);
        inOrder.verify(repository).save(newUser);
        verify(passwordEncryptor).encrypt(password);
        assertEquals(encryptedPassword, saveUser.getPassword());
    }
}