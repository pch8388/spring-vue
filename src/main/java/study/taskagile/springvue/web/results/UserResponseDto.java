package study.taskagile.springvue.web.results;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import study.taskagile.springvue.domain.model.user.User;

@RequiredArgsConstructor
@Getter
public class UserResponseDto {

    private final Long id;
    private final String username;
    private final String emailAddress;

    public static UserResponseDto entityToDto(User user) {
         return new UserResponseDto(
             user.getId(), user.getUsername(), user.getEmailAddress());
    }
}
