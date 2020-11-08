package study.taskagile.springvue.domain.common.mail;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class SimpleMessage implements Message {
    private String to;
    private String subject;
    private String from;
    private String body;

    public SimpleMessage(String to, String subject, String from, String body) {
        this.to = to;
        this.subject = subject;
        this.from = from;
        this.body = body;
    }
}
