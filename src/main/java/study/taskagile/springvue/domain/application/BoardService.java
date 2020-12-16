package study.taskagile.springvue.domain.application;

import study.taskagile.springvue.domain.application.command.CreateBoardCommand;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.user.User;

import java.util.List;

public interface BoardService {
    Board createBoard(CreateBoardCommand command);

    List<Board> findBoardsByMembership(Long userId);

    Board findBoardById(Long boardId);

    List<User> findMembers(Long boardId);

    User addMember(Long boardId, String usernameOrEmailAddress);
}
