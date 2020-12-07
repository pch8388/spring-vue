package study.taskagile.springvue.domain.application;

import study.taskagile.springvue.domain.application.command.CreateBoardCommand;
import study.taskagile.springvue.domain.model.board.Board;

import java.util.List;

public interface BoardService {
    Board createBoard(CreateBoardCommand command);

    List<Board> findBoardsByMembership(Long userId);
}
