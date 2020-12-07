package study.taskagile.springvue.web.apis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.taskagile.springvue.domain.application.BoardService;
import study.taskagile.springvue.domain.application.TeamService;
import study.taskagile.springvue.domain.common.security.CurrentUser;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.team.Team;
import study.taskagile.springvue.domain.model.user.SimpleUser;
import study.taskagile.springvue.web.results.ApiResult;

import java.util.List;

import static study.taskagile.springvue.web.results.ApiResult.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MeApiController {

    private final TeamService teamService;
    private final BoardService boardService;

    @GetMapping("/api/me")
    public ApiResult<MeResponseDto> getMyData(@CurrentUser SimpleUser currentUser) {
        List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
        List<Board> boards = boardService.findBoardsByMembership(currentUser.getUserId());
        return OK(new MeResponseDto(currentUser, teams, boards));
    }
}
