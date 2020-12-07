package study.taskagile.springvue.domain.model.team;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.taskagile.springvue.domain.common.model.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "team")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long teamId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "archived")
    private boolean archived;

    public static Team create(String name, Long creatorId) {
        Team team = new Team();
        team.name = name;
        team.archived = false;
        team.userId = creatorId;
        return team;
    }
}
