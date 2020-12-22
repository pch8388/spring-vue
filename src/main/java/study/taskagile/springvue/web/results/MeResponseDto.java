package study.taskagile.springvue.web.results;

import lombok.Getter;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.team.Team;
import study.taskagile.springvue.domain.model.user.SimpleUser;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Getter
public class MeResponseDto {

    private UserData userData;
    private Settings settings;
    private List<TeamResponse> teams;
    private List<BoardResponse> boards;


    public MeResponseDto(SimpleUser currentUser, List<Team> teams, List<Board> boards,
                         String realTimeServerUrl, String realTimeToken) {
        this.userData = new UserData(currentUser.getUsername(), realTimeToken);
        this.settings = new Settings(realTimeServerUrl);

        this.teams = teams.stream()
            .map(TeamResponse::new)
            .collect(toList());

        this.boards = boards.stream()
            .map(BoardResponse::new)
            .collect(toList());
    }

    @Getter
    private static class Settings {
        private String realTimeServerUrl;

        public Settings(String realTimeServerUrl) {
            this.realTimeServerUrl = realTimeServerUrl;
        }
    }

    @Getter
    private static class UserData {
        private String name;
        private String token;

        public UserData(String name, String token) {
            this.name = name;
            this.token = token;
        }
    }

    @Getter
    private static class TeamResponse {
        private Long id;
        private String name;

        TeamResponse(Team team) {
            this.id = team.getTeamId();
            this.name = team.getName();
        }
    }

    @Getter
    private static class BoardResponse {
        private Long id;
        private String name;
        private String description;
        private Long teamId;

        BoardResponse(Board board) {
            this.id = board.getId();
            this.name = board.getName();
            this.description = board.getDescription();
            this.teamId = board.getTeamId();
        }
    }
}
