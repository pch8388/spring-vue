package study.taskagile.springvue.domain.application;

import study.taskagile.springvue.domain.application.command.CreateTeamCommand;
import study.taskagile.springvue.domain.model.team.Team;

import java.util.List;

public interface TeamService {
    List<Team> findTeamsByUserId(Long userId);

    Team createTeam(CreateTeamCommand command);
}
