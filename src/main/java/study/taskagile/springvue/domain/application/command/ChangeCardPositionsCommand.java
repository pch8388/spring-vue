package study.taskagile.springvue.domain.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.taskagile.springvue.domain.model.cardList.CardListPosition;

import java.util.List;

@Getter
@AllArgsConstructor
public class ChangeCardPositionsCommand {
    private Long boardId;
    private List<CardListPosition> cardListPositions;
}
