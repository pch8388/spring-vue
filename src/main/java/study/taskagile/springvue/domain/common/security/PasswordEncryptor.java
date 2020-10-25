package study.taskagile.springvue.domain.common.security;

public interface PasswordEncryptor {
    String encrypt(String rawPassword);
}
