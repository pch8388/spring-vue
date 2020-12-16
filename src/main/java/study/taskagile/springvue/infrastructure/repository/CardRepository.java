package study.taskagile.springvue.infrastructure.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import study.taskagile.springvue.domain.model.card.Card;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {

    @Query(
        value = "select c.* from card c left join card_list cl on c.card_list_id = cl.id where cl.board_id = :boardId",
        nativeQuery = true)
    List<Card> findByBoardId(Long boardId);
}
