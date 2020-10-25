package study.taskagile.springvue.domain.application.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@Getter
@ToString
@EqualsAndHashCode
public class RegistrationCommand {

    private String username;
    private String emailAddress;
    private String password;

    public RegistrationCommand(String username, String emailAddress, String password) {
        Assert.hasText(username, "Parameter username must not be empty");
        Assert.hasText(emailAddress, "Parameter emailAddress must not be empty");
        Assert.hasText(password, "Parameter password must not be empty");

        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }
}
