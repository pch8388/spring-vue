package study.taskagile.springvue.web.apis;

import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.team.Team;
import study.taskagile.springvue.domain.model.user.SimpleUser;

import java.util.List;

public class MeResponseDto {
    public MeResponseDto(SimpleUser currentUser, List<Team> teams, List<Board> boards) {
    }
}
