package study.taskagile.springvue.domain.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddCardListCommand {

    private Long boardId;
    private Long userId;
    private String name;
    private int position;

}
