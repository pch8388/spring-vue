package study.taskagile.springvue.web.payload;

import lombok.Getter;
import study.taskagile.springvue.domain.application.command.ChangeCardListPositionsCommand;
import study.taskagile.springvue.domain.model.cardList.CardListPosition;

import java.util.List;

@Getter
public class ChangeCardListPositionsPayload {

    private Long boardId;
    private List<CardListPosition> cardListPositions;

    public ChangeCardListPositionsCommand toCommand() {
        return new ChangeCardListPositionsCommand(boardId, cardListPositions);
    }
}
