package study.taskagile.springvue.web.payload;

import study.taskagile.springvue.domain.application.command.CreateTeamCommand;

public class CreateTeamPayload {

    private String name;

    public CreateTeamCommand toCommand(Long userId) {
        return new CreateTeamCommand(name, userId);
    }
}
