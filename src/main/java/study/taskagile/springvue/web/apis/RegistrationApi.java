package study.taskagile.springvue.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import study.taskagile.springvue.domain.application.UserService;
import study.taskagile.springvue.domain.model.user.User;
import study.taskagile.springvue.web.payload.RegistrationPayload;
import study.taskagile.springvue.web.results.ApiResult;

import javax.validation.Valid;

import static study.taskagile.springvue.web.results.ApiResult.OK;

@RestController
@RequiredArgsConstructor
public class RegistrationApi {

    private final UserService userService;

    @PostMapping("/api/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<User> register(@Valid @RequestBody RegistrationPayload payload) {
        return OK(userService.register(payload.toCommand()));
    }
}
