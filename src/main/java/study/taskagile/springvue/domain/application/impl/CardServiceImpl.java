package study.taskagile.springvue.domain.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.taskagile.springvue.domain.application.CardService;
import study.taskagile.springvue.domain.application.command.AddCardCommand;
import study.taskagile.springvue.domain.common.event.DomainEventPublisher;
import study.taskagile.springvue.domain.model.card.Card;
import study.taskagile.springvue.domain.model.card.events.CardAddedEvent;
import study.taskagile.springvue.infrastructure.repository.CardRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public List<Card> findByBoardId(Long boardId) {
        return cardRepository.findByBoardId(boardId);
    }

    @Override
    public Card addCard(AddCardCommand command) {
        final Card card = Card.create(
            command.getCardListId(), command.getUserId(),
            command.getTitle(), command.getPosition());

        cardRepository.save(card);
        domainEventPublisher.publish(new CardAddedEvent(this, card));
        return card;
    }
}
