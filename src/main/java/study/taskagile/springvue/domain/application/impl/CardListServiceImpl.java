package study.taskagile.springvue.domain.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.taskagile.springvue.domain.application.CardListService;
import study.taskagile.springvue.domain.application.command.AddCardListCommand;
import study.taskagile.springvue.domain.application.command.ChangeCardListPositionsCommand;
import study.taskagile.springvue.domain.common.event.DomainEventPublisher;
import study.taskagile.springvue.domain.model.cardList.CardList;
import study.taskagile.springvue.domain.model.cardList.events.CardListAddedEvent;
import study.taskagile.springvue.infrastructure.repository.CardListRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardListServiceImpl implements CardListService {

    private final CardListRepository cardListRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public List<CardList> findByBoardId(Long boardId) {
        return cardListRepository.findByBoardId(boardId);
    }

    @Override
    public CardList addCardList(AddCardListCommand command) {
        final CardList cardList = CardList.create(
            command.getBoardId(), command.getUserId(),
            command.getName(), command.getPosition());

        cardListRepository.save(cardList);
        domainEventPublisher.publish(new CardListAddedEvent(this, cardList));
        return cardList;
    }

    @Override
    public void changePositions(ChangeCardListPositionsCommand command) {
        command.getCardListPositions().forEach(cardListRepository::changePositions);
    }
}
