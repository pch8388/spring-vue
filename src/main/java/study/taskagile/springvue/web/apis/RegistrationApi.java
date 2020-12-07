package study.taskagile.springvue.web.apis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import study.taskagile.springvue.domain.application.UserService;
import study.taskagile.springvue.domain.model.user.RegistrationException;
import study.taskagile.springvue.domain.model.user.User;
import study.taskagile.springvue.web.payload.RegistrationPayload;
import study.taskagile.springvue.web.results.ApiResult;
import study.taskagile.springvue.web.results.UserResponseDto;

import javax.validation.Valid;

import java.util.Optional;

import static study.taskagile.springvue.utils.LoggerFormatUtils.API_ERROR_MESSAGE;
import static study.taskagile.springvue.web.results.ApiResult.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegistrationApi {

    private final UserService userService;

    @PostMapping("/api/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<UserResponseDto> register(@Valid @RequestBody RegistrationPayload payload) {
        return OK(
            Optional.ofNullable(userService.register(payload.toCommand()))
                .map(UserResponseDto::entityToDto)
                .orElseThrow(() -> {
                    log.error(API_ERROR_MESSAGE, this.getClass().getName(), "register", "register");
                    return new RegistrationException("user register api exception!");
                })
        );
    }
}
