package study.taskagile.springvue.domain.common.mail;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class MessageVariable {
    private String key;
    private Object value;

    private MessageVariable(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public static MessageVariable from(String key, Object value) {
        return new MessageVariable(key, value);
    }
}
