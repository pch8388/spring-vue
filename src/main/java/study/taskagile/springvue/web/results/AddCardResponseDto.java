package study.taskagile.springvue.web.results;

import lombok.Getter;
import study.taskagile.springvue.domain.model.card.Card;

@Getter
public class AddCardResponseDto {

    private final Long id;
    private final String title;
    private final int position;

    public AddCardResponseDto(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.position = card.getPosition();
    }
}
