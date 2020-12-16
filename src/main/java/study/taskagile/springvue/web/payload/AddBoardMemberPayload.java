package study.taskagile.springvue.web.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class AddBoardMemberPayload {

    private String usernameOrEmailAddress;

    @Builder
    public AddBoardMemberPayload(String usernameOrEmailAddress) {
        this.usernameOrEmailAddress = usernameOrEmailAddress;
    }
}
