package study.taskagile.springvue.web.results;

import lombok.Getter;
import study.taskagile.springvue.domain.model.cardList.CardList;

@Getter
public class AddCardListResponseDto {

    private Long id;
    private String name;

    public AddCardListResponseDto(CardList cardList) {
        this.id = cardList.getId();
        this.name = cardList.getName();
    }
}
