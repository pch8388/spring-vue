package study.taskagile.springvue.domain.model.card.events;

import lombok.Getter;
import study.taskagile.springvue.domain.common.event.DomainEvent;
import study.taskagile.springvue.domain.model.card.Card;

public class CardAddedEvent extends DomainEvent {

    @Getter
    private Card card;

    public CardAddedEvent(Object source, Card card) {
        super(source);
        this.card = card;
    }
}
