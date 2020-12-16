package study.taskagile.springvue.domain.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.taskagile.springvue.domain.application.CardService;
import study.taskagile.springvue.domain.model.card.Card;
import study.taskagile.springvue.infrastructure.repository.CardRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public List<Card> findByBoardId(Long boardId) {
        return cardRepository.findByBoardId(boardId);
    }
}
