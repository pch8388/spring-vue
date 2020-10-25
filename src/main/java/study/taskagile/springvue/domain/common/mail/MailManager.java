package study.taskagile.springvue.domain.common.mail;

public interface MailManager {
    void send(String emailAddress, String subject, String template,
              MessageVariable... messageVariables);
}
