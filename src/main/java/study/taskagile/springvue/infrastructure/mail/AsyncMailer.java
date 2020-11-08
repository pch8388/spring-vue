package study.taskagile.springvue.infrastructure.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import study.taskagile.springvue.domain.common.mail.Mailer;
import study.taskagile.springvue.domain.common.mail.Message;

@Component
@RequiredArgsConstructor
@Slf4j
public class AsyncMailer implements Mailer {

    private final JavaMailSender mailSender;

    @Async
    @Override
    public void send(Message message) {
        Assert.notNull(message, "Parameter `message` must not be null");

        try {
            mailSender.send(createMessage(message));
        } catch (MailException e) {
            log.error("Failed to send mail message", e);
        }
    }

    private SimpleMailMessage createMessage(Message message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        if (isNotBlank(message.getFrom())) {
            mailMessage.setFrom(message.getFrom());
        }

        if (isNotBlank(message.getSubject())) {
            mailMessage.setSubject(message.getSubject());
        }

        if (isNotEmpty(message.getBody())) {
            mailMessage.setSubject(message.getBody());
        }

        if (message.getTo() != null) {
            mailMessage.setTo(message.getTo());
        }

        return mailMessage;
    }

    private boolean isNotBlank(String target) {
        return target != null && !target.isBlank();
    }

    private boolean isNotEmpty(String target) {
        return target != null && !target.isEmpty();
    }
}
