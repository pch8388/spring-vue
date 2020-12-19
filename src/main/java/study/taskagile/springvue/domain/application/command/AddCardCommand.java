package study.taskagile.springvue.domain.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddCardCommand {
    private Long cardListId;
    private Long userId;
    private String title;
    private int position;
}
