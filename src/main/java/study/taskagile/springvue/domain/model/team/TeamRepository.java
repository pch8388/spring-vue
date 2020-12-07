package study.taskagile.springvue.domain.model.team;

import java.util.List;

public interface TeamRepository {
    List<Team> findTeamsByUserId(Long userId);

    Team save(Team team);
}
