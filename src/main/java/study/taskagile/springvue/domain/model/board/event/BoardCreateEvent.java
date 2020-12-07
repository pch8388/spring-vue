package study.taskagile.springvue.domain.model.board.event;

import lombok.Getter;
import study.taskagile.springvue.domain.application.impl.BoardServiceImpl;
import study.taskagile.springvue.domain.common.event.DomainEvent;
import study.taskagile.springvue.domain.model.board.Board;

@Getter
public class BoardCreateEvent extends DomainEvent {

    private Board board;

    public BoardCreateEvent(Object source, Board board) {
        super(source);
        this.board = board;
    }
}
