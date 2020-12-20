package study.taskagile.springvue.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.taskagile.springvue.domain.application.TeamService;
import study.taskagile.springvue.domain.common.security.CurrentUser;
import study.taskagile.springvue.domain.model.user.SimpleUser;
import study.taskagile.springvue.web.payload.CreateTeamPayload;
import study.taskagile.springvue.web.results.ApiResult;
import study.taskagile.springvue.web.results.CreateTeamResponseDto;

import static study.taskagile.springvue.web.results.ApiResult.OK;

@RestController
@RequiredArgsConstructor
public class TeamApiController {

    private final TeamService teamService;

    @PostMapping("/api/teams")
    public ApiResult<?> createTeam(
        @RequestBody CreateTeamPayload payload,
        @CurrentUser SimpleUser currentUser) {

        return OK(new CreateTeamResponseDto(
            teamService.createTeam(payload.toCommand(currentUser.getUserId())))
        );
    }
}
