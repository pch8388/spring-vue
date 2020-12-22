package study.taskagile.springvue.web.socket;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import study.taskagile.springvue.domain.common.security.TokenManager;
import study.taskagile.springvue.utils.JsonUtils;

@Component
@RequiredArgsConstructor
public class WebSocketRequestDispatcher extends TextWebSocketHandler {

    private final TokenManager tokenManager;
    private final ChannelHandlerResolver channelHandlerResolver;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        final RealTimeSession realTimeSession = new RealTimeSession(session);
        String token = realTimeSession.getToken();

        try {
            final Long userId = tokenManager.verifyJwt(token);
            realTimeSession.setUserId(userId);
            realTimeSession.reply("authenticated");
        } catch (JwtException exception) {
            realTimeSession.fail("authentication failed");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        RealTimeSession realTimeSession = new RealTimeSession(session);

        final IncomingMessage incomingMessage = JsonUtils.toObject(message.getPayload(), IncomingMessage.class);
        if (incomingMessage == null) {
            realTimeSession.error("Illegal format of incoming message: " + message.getPayload());
            return;
        }

        ChannelHandlerInvoker invoker = channelHandlerResolver.findInvoker(incomingMessage);
        if (invoker == null) {
            String errorMessage = "No handler found for action `" + incomingMessage.getAction()
                + "` at channel `" + incomingMessage.getChannel() + "`";
            realTimeSession.error(errorMessage);
            return;
        }

        invoker.handle(incomingMessage, realTimeSession);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        final RealTimeSession realTimeSession = new RealTimeSession(session);
        SubscriptionHub.unsubscribeAll(realTimeSession);
    }
}
