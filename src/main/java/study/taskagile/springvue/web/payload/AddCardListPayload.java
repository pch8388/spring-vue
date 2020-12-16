package study.taskagile.springvue.web.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.taskagile.springvue.domain.application.command.AddCardListCommand;

@Getter
@NoArgsConstructor
public class AddCardListPayload {

    private Long boardId;
    private String name;
    private int position;

    @Builder
    public AddCardListPayload(Long boardId, String name, int position) {
        this.boardId = boardId;
        this.name = name;
        this.position = position;
    }

    public AddCardListCommand toCommand(Long userId) {
        return new AddCardListCommand(boardId, userId, name, position);
    }
}
