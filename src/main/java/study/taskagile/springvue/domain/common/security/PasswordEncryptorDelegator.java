package study.taskagile.springvue.domain.common.security;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptorDelegator implements PasswordEncryptor {
    @Override
    public String encrypt(String rawPassword) {
        // TODO : 2020/10/25 구현필요   -ksc
        return rawPassword;
    }
}
