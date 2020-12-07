package study.taskagile.springvue.web.results;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import study.taskagile.springvue.domain.model.board.Board;

@RequiredArgsConstructor
@Getter
public class BoardResponseDto {

    private final Long id;
    private final String name;
    private final String description;
    private final Long teamId;

    public static BoardResponseDto entityToDto(Board board) {
        return new BoardResponseDto(
            board.getId(), board.getName(),
            board.getDescription(), board.getTeamId());
    }
}
