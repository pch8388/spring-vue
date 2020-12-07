package study.taskagile.springvue.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import study.taskagile.springvue.domain.model.team.Team;
import study.taskagile.springvue.domain.model.team.TeamRepository;

public interface JpaTeamRepository extends CrudRepository<Team, Long>, TeamRepository {
}
