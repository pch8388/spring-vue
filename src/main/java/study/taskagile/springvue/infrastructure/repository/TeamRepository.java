package study.taskagile.springvue.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import study.taskagile.springvue.domain.model.team.Team;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    List<Team> findTeamsByUserId(Long userId);
}
