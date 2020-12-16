package study.taskagile.springvue.infrastructure.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import study.taskagile.springvue.domain.model.cardList.CardList;
import study.taskagile.springvue.domain.model.cardList.CardListPosition;

import java.util.List;

public interface CardListRepository extends CrudRepository<CardList, Long> {

    @Query(
        value = "update card_list set `position` = :#{#cardListPosition.position} where id = :#{#cardListPosition.cardListId}",
        nativeQuery = true)
    void changePositions(@Param("cardListPosition") CardListPosition cardListPosition);

    List<CardList> findByBoardId(Long boardId);
}
