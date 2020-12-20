package study.taskagile.springvue.domain.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.taskagile.springvue.domain.model.CardPosition;

import java.util.List;

@AllArgsConstructor
@Getter
public class ChangeCardPositionsCommand {
    private Long boardId;
    private List<CardPosition> cardPositions;
}
