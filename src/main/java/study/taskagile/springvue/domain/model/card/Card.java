package study.taskagile.springvue.domain.model.card;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.taskagile.springvue.domain.common.model.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "card")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Card extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "card_list_id")
    private Long cardListId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "position")
    private int position;

    @Column(name = "archived")
    private boolean archived;

    private Card(Long cardListId, Long userId, String title, int position) {

        this.cardListId = cardListId;
        this.userId = userId;
        this.title = title;
        this.description = "";
        this.position = position;
        this.archived = false;
    }

    public static Card create(Long cardListId, Long userId, String title, int position) {
        return new Card(cardListId, userId, title, position);
    }

    public void changePosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("not valid position : " + position);
        }
        this.position = position;
    }
}
