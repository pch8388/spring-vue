package study.taskagile.springvue.web.payload;

import study.taskagile.springvue.domain.application.command.CreateBoardCommand;

public class CreateBoardPayload {

    private String name;
    private String description;
    private Long teamId;

    public CreateBoardCommand toCommand(Long userId) {
        return new CreateBoardCommand(userId, name, description, teamId);
    }
}
