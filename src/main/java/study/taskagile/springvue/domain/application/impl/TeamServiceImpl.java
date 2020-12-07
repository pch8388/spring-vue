package study.taskagile.springvue.domain.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.taskagile.springvue.domain.application.TeamService;
import study.taskagile.springvue.domain.application.command.CreateTeamCommand;
import study.taskagile.springvue.domain.common.event.DomainEventPublisher;
import study.taskagile.springvue.domain.model.team.Team;
import study.taskagile.springvue.domain.model.team.TeamRepository;
import study.taskagile.springvue.domain.model.team.event.TeamCreateEvent;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public List<Team> findTeamsByUserId(Long userId) {
        return teamRepository.findTeamsByUserId(userId);
    }

    @Override
    public Team createTeam(CreateTeamCommand command) {
        Team team = teamRepository.save(
            Team.create(command.getName(), command.getUserId()));
        domainEventPublisher.publish(new TeamCreateEvent(this, team));
        return team;
    }
}
