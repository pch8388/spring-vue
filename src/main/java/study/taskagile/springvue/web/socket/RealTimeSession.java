package study.taskagile.springvue.web.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import study.taskagile.springvue.utils.JsonUtils;

import java.io.IOException;
import java.net.URI;

@RequiredArgsConstructor
public class RealTimeSession {

    private static final String KEY_USER_ID = "KEY_USER_ID";

    private final WebSocketSession session;

    public String id() {
        return session.getId();
    }

    public WebSocketSession wrapped() {
        return session;
    }

    public void setUserId(Long userId) {
        addAttribute(KEY_USER_ID, userId);
    }

    public Long getUserId() {
        return getAttribute(KEY_USER_ID);
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(String key) {
        final Object value = session.getAttributes().get(key);
        if (value == null) {
            return null;
        }
        return (T) value;
    }

    private void addAttribute(String key, Object value) {
        session.getAttributes().put(key, value);
    }

    public String getToken() {
        URI uri = session.getUri();
        assert uri != null;
        UriComponents uriComponents = UriComponentsBuilder.fromUri(uri).build();
        return uriComponents.getQueryParams().getFirst("token");
    }

    public void error(String error) {
        sendMessage(WebSocketMessages.error(error));
    }

    public void reply(String reply) {
        sendMessage(WebSocketMessages.reply(reply));
    }

    public void fail(String failure) {
        sendMessage(WebSocketMessages.failure(failure));
    }

    private void sendMessage(Object message) {
        String textMessage = JsonUtils.toJson(message);
        try {
            assert textMessage != null;
            session.sendMessage(new TextMessage(textMessage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
