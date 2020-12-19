package study.taskagile.springvue.web.socket;

import org.springframework.web.socket.TextMessage;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static study.taskagile.springvue.utils.JsonUtils.toJson;

public class WebSocketMessages {

    static TextMessage reply(String reply) {
        Map<String, String> messageObject = new HashMap<>();
        messageObject.put("type", "reply");
        messageObject.put("message", reply);
        return new TextMessage(requireNonNull(toJson(messageObject)));
    }

    static TextMessage error(String error) {
        Map<String, String> messageObject = new HashMap<>();
        messageObject.put("type", "error");
        messageObject.put("message", error);
        return new TextMessage(requireNonNull(toJson(messageObject)));
    }

    static TextMessage failure(String failure) {
        Map<String, String> messageObject = new HashMap<>();
        messageObject.put("type", "failure");
        messageObject.put("message", failure);
        return new TextMessage(requireNonNull(toJson(messageObject)));
    }

    static TextMessage channelMessage(String channel, String payload) {
        Map<String, String> messageObject = new HashMap<>();
        messageObject.put("channel", channel);
        messageObject.put("payload", payload);
        return new TextMessage(requireNonNull(toJson(messageObject)));
    }
}
