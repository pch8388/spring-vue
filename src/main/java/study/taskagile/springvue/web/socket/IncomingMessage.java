package study.taskagile.springvue.web.socket;

import lombok.Getter;

@Getter
public class IncomingMessage {

    private String channel;
    private String action;
    private String payload;

    private IncomingMessage(String channel, String action, String payload) {
        this.channel = channel;
        this.action = action;
        this.payload = payload;
    }

    public static IncomingMessage create(String channel, String action, String payload) {
        return new IncomingMessage(channel, action, payload);
    }

}
