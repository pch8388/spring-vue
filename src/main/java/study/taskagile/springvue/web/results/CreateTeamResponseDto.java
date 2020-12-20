package study.taskagile.springvue.web.results;

import lombok.Getter;
import study.taskagile.springvue.domain.model.team.Team;

@Getter
public class CreateTeamResponseDto {

    private Long id;
    private String name;

    public CreateTeamResponseDto(Team team) {
       this.id = team.getTeamId();
       this.name = team.getName();
    }
}
