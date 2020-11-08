package study.taskagile.springvue.web.apis.authenticate;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import study.taskagile.springvue.utils.JsonUtils;
import study.taskagile.springvue.web.results.ApiResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static study.taskagile.springvue.web.results.ApiResult.ERROR;

public class SimpleAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
        HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException {

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        JsonUtils.write(response.getWriter(), generateApiResult(exception));
    }

    private ApiResult<?> generateApiResult(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException) {
            return ERROR("Invalid Credentials", HttpStatus.BAD_REQUEST);
        } else if (exception instanceof InsufficientAuthenticationException) {
            return ERROR("Invalid authentication request", HttpStatus.BAD_REQUEST);
        }
        return ERROR("Authentication failure", HttpStatus.BAD_REQUEST);
    }
}
