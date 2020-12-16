package study.taskagile.springvue.domain.model.cardList.events;

import lombok.Getter;
import study.taskagile.springvue.domain.common.event.DomainEvent;
import study.taskagile.springvue.domain.model.cardList.CardList;

public class CardListAddedEvent extends DomainEvent {

    @Getter
    private CardList cardList;

    public CardListAddedEvent(Object source, CardList cardList) {
        super(source);
        this.cardList = cardList;
    }
}
