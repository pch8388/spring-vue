package study.taskagile.springvue.web.updater;

import org.springframework.stereotype.Component;
import study.taskagile.springvue.domain.model.card.Card;
import study.taskagile.springvue.utils.JsonUtils;
import study.taskagile.springvue.web.socket.SubscriptionHub;

import java.util.HashMap;
import java.util.Map;

import static study.taskagile.springvue.utils.JsonUtils.*;

@Component
public class CardUpdater {

    public void onCardAdded(Long boardId, Card card) {
        Map<String, Object> cardData = new HashMap<>();
        cardData.put("id", card.getId());
        cardData.put("title", card.getTitle());
        cardData.put("cardListId", card.getCardListId());
        cardData.put("position", card.getPosition());

        Map<String, Object> update = new HashMap<>();
        update.put("type", "cardAdded");
        update.put("card", cardData);

        SubscriptionHub.send("/board/" + boardId, toJson(update));
    }
}
