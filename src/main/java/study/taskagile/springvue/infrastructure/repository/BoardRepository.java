package study.taskagile.springvue.infrastructure.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import study.taskagile.springvue.domain.model.board.Board;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
    @Query(
        value = "select b.* from board b left join board_member bm on b.id = bm.board_id where bm.user_id = :userId",
        nativeQuery = true)
    List<Board> findBoardsByMembership(Long userId);
}
