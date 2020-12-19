package study.taskagile.springvue.web.payload;

import lombok.Getter;
import study.taskagile.springvue.domain.application.command.ChangeCardPositionsCommand;
import study.taskagile.springvue.domain.model.cardList.CardListPosition;

import java.util.List;

@Getter
public class ChangeCardListPositionsPayload {

    private Long boardId;
    private List<CardListPosition> cardListPositions;

    public ChangeCardPositionsCommand toCommand() {
        return new ChangeCardPositionsCommand(boardId, cardListPositions);
    }
}
