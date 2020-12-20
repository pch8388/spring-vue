package study.taskagile.springvue.web.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.taskagile.springvue.domain.application.command.CreateBoardCommand;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateBoardPayload {

    @Size(min = 2, max = 50, message = "Board name must be between 2 and 50 characters")
    @NotNull
    private String name;

    @Size(max = 255)
    private String description;
    private Long teamId;

    public CreateBoardCommand toCommand(Long userId) {
        return new CreateBoardCommand(userId, name, description, teamId);
    }
}
