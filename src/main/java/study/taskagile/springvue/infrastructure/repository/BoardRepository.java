package study.taskagile.springvue.infrastructure.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.user.User;
import study.taskagile.springvue.web.results.BoardResponseDto;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
    @Query(
        value = "select b.* from board b left join board_member bm on b.board_id = bm.board_id where bm.user_id = :userId",
        nativeQuery = true)
    List<Board> findBoardsByMembership(Long userId);

    @Query(
        value = "select u.* from board_member bm left join user u on u.user_id = bm.user_id where bm.board_id = :boardId",
        nativeQuery = true)
    List<User> findMembers(Long boardId);
}
