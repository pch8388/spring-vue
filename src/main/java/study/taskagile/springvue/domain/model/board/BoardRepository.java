package study.taskagile.springvue.domain.model.board;

import java.util.List;

public interface BoardRepository {
    List<Board> findBoardsByMembership(Long userId);

    Board save(Board board);
}
