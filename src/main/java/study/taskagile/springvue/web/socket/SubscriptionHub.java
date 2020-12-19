package study.taskagile.springvue.web.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static study.taskagile.springvue.web.socket.WebSocketMessages.*;

@Slf4j
public final class SubscriptionHub {

    private static final Map<String, Set<WebSocketSession>> subscriptions = new HashMap<>();
    private static final Map<String, Set<String>> subscribedChannels = new HashMap<>();


    public static void send(String channel, String update) {
        Assert.hasText(channel, "Parameter `channel` must not be empty");
        Assert.hasText(update, "Parameter `update` must not be empty");

        Set<WebSocketSession> subscribers = subscriptions.get(channel);
        if (subscribers == null || subscribers.isEmpty()) {
            log.debug("No subscribers of channel `{}` found", channel);
            return;
        }

        for (WebSocketSession subscriber : subscribers) {
            sendTo(subscriber, channel, update);
        }
    }

    private static void sendTo(WebSocketSession subscriber, String channel, String update) {
        try {
            subscriber.sendMessage(channelMessage(channel, update));
        } catch (IOException e) {
            log.error("Failed to send message to subscriber `"
                + subscriber.getId() + "` of channel `" + channel
                + "`, Message: " + update, e);
        }
    }
}
