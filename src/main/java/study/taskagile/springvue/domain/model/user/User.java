package study.taskagile.springvue.domain.model.user;


import lombok.*;
import org.springframework.util.Assert;
import study.taskagile.springvue.domain.common.model.BaseTimeEntity;

import javax.persistence.*;

@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString(of = {"username", "emailAddress", "password"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "email_address", nullable = false, length = 100, unique = true)
    private String emailAddress;

    @Column(name = "password", nullable = false, length = 80)
    private String password;

    private User(String username, String emailAddress, String password) {
        Assert.notNull(username, "Parameter username must be not null");
        Assert.notNull(emailAddress, "Parameter emailAddress must be not null");
        Assert.notNull(password, "Parameter password must be not null");

        this.username = username;
        this.emailAddress = emailAddress.toLowerCase();
        this.password = password;
    }

    public static User create(String username, String emailAddress, String password) {
        return new User(username, emailAddress, password);
    }
}
