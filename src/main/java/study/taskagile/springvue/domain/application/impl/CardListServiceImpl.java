package study.taskagile.springvue.domain.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.taskagile.springvue.domain.application.CardListService;
import study.taskagile.springvue.domain.model.cardList.CardList;
import study.taskagile.springvue.infrastructure.repository.CardListRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardListServiceImpl implements CardListService {

    private final CardListRepository cardListRepository;

    @Override
    public List<CardList> findByBoardId(Long boardId) {
        return cardListRepository.findByBoardId(boardId);
    }
}
