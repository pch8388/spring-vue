package study.taskagile.springvue.domain.common.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class DefaultMailManager implements MailManager {

    private String mailFrom;
    private Mailer mailer;
    private Configuration configuration;

    public DefaultMailManager(@Value("${app.mail-from}") String mailFrom,
                              Mailer mailer,
                              Configuration configuration) {
        this.mailFrom = mailFrom;
        this.mailer = mailer;
        this.configuration = configuration;
    }

    @Override
    public void send(String emailAddress, String subject, String template,
                     MessageVariable... messageVariables) {

        Assert.hasText(emailAddress, "Parameter `emailAddress` must not empty");
        Assert.hasText(subject, "Parameter `subject` must not empty");
        Assert.hasText(template, "Parameter `template` must not empty");

        String messageBody = createMessageBody(template, messageVariables);
        Message message = new SimpleMessage(emailAddress, subject, mailFrom, messageBody);
        mailer.send(message);
    }

    private String createMessageBody(String templateName, MessageVariable... variables) {
        try {
            Template template = configuration.getTemplate(templateName);
            Map<String, Object> model = new HashMap<>();
            if (variables != null) {
                for (MessageVariable variable : variables) {
                    model.put(variable.getKey(), variable.getValue());
                }
            }
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException | TemplateException e) {
            log.error("Failed to create message body from template `" + templateName + "`", e);
            return null;
        }
    }
}
