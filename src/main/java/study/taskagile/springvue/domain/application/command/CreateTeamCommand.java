package study.taskagile.springvue.domain.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateTeamCommand {
    private String name;
    private Long userId;
}
