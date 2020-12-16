package study.taskagile.springvue.domain.model.board.event;

import lombok.Getter;
import study.taskagile.springvue.domain.common.event.DomainEvent;
import study.taskagile.springvue.domain.model.user.User;

@Getter
public class BoardMemberAddEvent extends DomainEvent {

    private Long boardId;
    private User user;

    public BoardMemberAddEvent(Object source, Long boardId, User user) {
        super(source);
        this.boardId = boardId;
        this.user = user;
    }
}
