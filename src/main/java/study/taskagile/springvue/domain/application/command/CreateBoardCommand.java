package study.taskagile.springvue.domain.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateBoardCommand {
    private Long userId;
    private String name;
    private String description;
    private Long teamId;
}
