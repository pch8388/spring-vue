package study.taskagile.springvue.domain.application;

import study.taskagile.springvue.domain.application.command.AddCardListCommand;
import study.taskagile.springvue.domain.application.command.ChangeCardListPositionsCommand;
import study.taskagile.springvue.domain.model.cardList.CardList;

import java.util.List;

public interface CardListService {
    List<CardList> findByBoardId(Long boardId);

    CardList addCardList(AddCardListCommand toCommand);

    void changePositions(ChangeCardListPositionsCommand toCommand);
}
