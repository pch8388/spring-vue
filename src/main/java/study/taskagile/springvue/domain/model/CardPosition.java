package study.taskagile.springvue.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CardPosition {

    private Long cardListId;
    private Long cardId;
    private int position;

    @Builder
    public CardPosition(Long cardListId, Long cardId, int position) {
        this.cardListId = cardListId;
        this.cardId = cardId;
        this.position = position;
    }
}
