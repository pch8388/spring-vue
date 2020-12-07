package study.taskagile.springvue.domain.model.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.taskagile.springvue.domain.common.model.BaseTimeEntity;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "archived")
    private boolean archived;

    public static Board create(Long userId, String name, String description, Long teamId) {
        Board board = new Board();
        board.userId = userId;
        board.name = name;
        board.description = description;
        board.teamId = Optional.ofNullable(teamId).orElse(0L);
        board.archived = false;
        return board;
    }
}
