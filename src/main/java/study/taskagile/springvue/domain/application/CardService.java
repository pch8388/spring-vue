package study.taskagile.springvue.domain.application;

import study.taskagile.springvue.domain.application.command.AddCardCommand;
import study.taskagile.springvue.domain.model.card.Card;

import java.util.List;

public interface CardService {
    List<Card> findByBoardId(Long boardId);

    Card addCard(AddCardCommand toCommand);
}
