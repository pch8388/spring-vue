package study.taskagile.springvue.web.apis.authenticate;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import study.taskagile.springvue.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static study.taskagile.springvue.web.results.ApiResult.OK;

public class SimpleLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(
        HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {

        response.setStatus(HttpStatus.OK.value());
        JsonUtils.write(response.getWriter(), OK("logged-out"));
    }
}
