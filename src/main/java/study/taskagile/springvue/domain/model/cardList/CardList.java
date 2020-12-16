package study.taskagile.springvue.domain.model.cardList;

import lombok.Getter;
import study.taskagile.springvue.domain.common.model.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "card_list")
@Getter
public class CardList extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "position")
    private int position;

    @Column(name = "archived")
    private boolean archived;

    public static CardList create(Long boardId, Long userId, String name, int position) {
        CardList cardList = new CardList();
        cardList.boardId = boardId;
        cardList.userId = userId;
        cardList.name = name;
        cardList.position = position;
        cardList.archived = false;
        return cardList;
    }
}
