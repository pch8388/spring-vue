package study.taskagile.springvue.domain.application;

import study.taskagile.springvue.domain.model.cardList.CardList;

import java.util.List;

public interface CardListService {
    List<CardList> findByBoardId(Long boardId);
}
