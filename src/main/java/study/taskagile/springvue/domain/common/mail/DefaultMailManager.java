package study.taskagile.springvue.domain.common.mail;

import org.springframework.stereotype.Component;

@Component
public class DefaultMailManager implements MailManager {
    @Override
    public void send(String emailAddress, String subject, String template,
                     MessageVariable... messageVariables) {
        // TODO : 2020/10/25 구현필요   -ksc
    }
}
