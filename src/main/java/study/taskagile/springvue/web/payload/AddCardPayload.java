package study.taskagile.springvue.web.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.taskagile.springvue.domain.application.command.AddCardCommand;

@NoArgsConstructor
@Getter
public class AddCardPayload {

    private Long boardId;
    private Long cardListId;
    private String title;
    private int position;

    public AddCardCommand toCommand(Long userId) {
        return new AddCardCommand(cardListId, userId, title, position);
    }

    @Builder
    public AddCardPayload(Long boardId, Long cardListId, String title, int position) {
        this.boardId = boardId;
        this.cardListId = cardListId;
        this.title = title;
        this.position = position;
    }
}
