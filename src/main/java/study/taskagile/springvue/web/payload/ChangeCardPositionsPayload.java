package study.taskagile.springvue.web.payload;

import lombok.Builder;
import lombok.Getter;
import study.taskagile.springvue.domain.application.command.ChangeCardPositionsCommand;
import study.taskagile.springvue.domain.model.CardPosition;

import java.util.List;

@Getter
public class ChangeCardPositionsPayload {

    private Long boardId;
    private List<CardPosition> cardPositions;

    public ChangeCardPositionsCommand toCommand() {
        return new ChangeCardPositionsCommand(boardId, cardPositions);
    }

    @Builder
    public ChangeCardPositionsPayload(Long boardId, List<CardPosition> cardPositions) {
        this.boardId = boardId;
        this.cardPositions = cardPositions;
    }
}
