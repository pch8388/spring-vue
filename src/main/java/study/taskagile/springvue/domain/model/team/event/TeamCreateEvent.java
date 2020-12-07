package study.taskagile.springvue.domain.model.team.event;

import lombok.Getter;
import study.taskagile.springvue.domain.common.event.DomainEvent;
import study.taskagile.springvue.domain.model.team.Team;

@Getter
public class TeamCreateEvent extends DomainEvent {

    private Team team;

    public TeamCreateEvent(Object source, Team team) {
        super(source);
        this.team = team;
    }
}
