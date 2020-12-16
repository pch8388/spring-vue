package study.taskagile.springvue.web.results;

import lombok.Getter;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.user.User;

@Getter
public class AddBoardMemberResponseDto {

    private Long id;
    private String shortName;

    public AddBoardMemberResponseDto(User user) {
        this.id = user.getId();
        this.shortName = user.getUsername();
    }
}
