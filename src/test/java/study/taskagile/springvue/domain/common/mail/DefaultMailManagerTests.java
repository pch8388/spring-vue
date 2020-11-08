package study.taskagile.springvue.domain.common.mail;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class DefaultMailManagerTests {

    @Mock
    private Mailer mailer;
    private DefaultMailManager mailManager;

    private final static String EMAIL_ADDRESS = "test@test.com";
    private final static String SUBJECT = "test@test.com";
    private final static String TEMPLATE = "test.ftl";
    private final static MessageVariable MESSAGE_VARIABLE = MessageVariable.from("name", "test");

    @BeforeEach
    void beforeEach() throws IOException, TemplateException {
        FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
        factoryBean.setTemplateLoaderPaths("/mail-templates/");
        factoryBean.afterPropertiesSet();

        Configuration configuration = factoryBean.getObject();
        mailManager = new DefaultMailManager("noreply@taskagile.com", mailer, configuration);
    }

    @Test
    @DisplayName("보낼 메일 주소가 null 이거나 비어있으면 예외 발생")
    void send_nullOrEmpty_emailAddress_fail() {
        assertThrows(IllegalArgumentException.class,
            () -> mailManager.send(null, SUBJECT, TEMPLATE, MESSAGE_VARIABLE));

        assertThrows(IllegalArgumentException.class,
            () -> mailManager.send("", SUBJECT, TEMPLATE, MESSAGE_VARIABLE));
    }

    @Test
    @DisplayName("제목이 null 이거나 비어있으면 예외 발생")
    void send_nullOrEmpty_subject_fail() {
        assertThrows(IllegalArgumentException.class,
            () -> mailManager.send(EMAIL_ADDRESS, null, TEMPLATE, MESSAGE_VARIABLE));

        assertThrows(IllegalArgumentException.class,
            () -> mailManager.send(EMAIL_ADDRESS, "", TEMPLATE, MESSAGE_VARIABLE));
    }

    @Test
    @DisplayName("템플릿이 null 이거나 비어있으면 예외 발생")
    void send_nullOrEmpty_template_fail() {
        assertThrows(IllegalArgumentException.class,
            () -> mailManager.send(EMAIL_ADDRESS, SUBJECT, null, MESSAGE_VARIABLE));

        assertThrows(IllegalArgumentException.class,
            () -> mailManager.send(EMAIL_ADDRESS, SUBJECT, "", MESSAGE_VARIABLE));
    }

    @Test
    @DisplayName("메시지 전송 성공")
    void send_validParameters_success() {
        mailManager.send(EMAIL_ADDRESS, SUBJECT, TEMPLATE, MESSAGE_VARIABLE);

        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(mailer).send(messageArgumentCaptor.capture());

        Message messageSent = messageArgumentCaptor.getValue();
        assertEquals(EMAIL_ADDRESS, messageSent.getTo());
        assertEquals(SUBJECT, messageSent.getSubject());
        assertEquals("noreply@taskagile.com", messageSent.getFrom());
        assertEquals("Hello, test", messageSent.getBody());
    }
}