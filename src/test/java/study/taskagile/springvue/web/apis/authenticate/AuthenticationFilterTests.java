package study.taskagile.springvue.web.apis.authenticate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationFilterTests {

    @Mock
    private AuthenticationManager authenticationManager;

    private MockHttpServletRequest request;
    @InjectMocks
    private AuthenticationFilter filter;

    @BeforeEach
    void beforeEach() {
        request = new MockHttpServletRequest("POST", "/api/authentications");
        filter = new AuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager);
    }

    @Test
    @DisplayName("request body 가 비어있으면 예외 발생")
    void attemptAuthentication_emptyRequestBody() {
        assertThrows(InsufficientAuthenticationException.class,
            () -> filter.attemptAuthentication(request, new MockHttpServletResponse()));

    }

    @Test
    @DisplayName("컨텐츠가 json 이 아니면 예외발생")
    void attemptAuthentication_invalidRequestBody() {
        request.setContent("username=test&password=testPassword".getBytes());
        assertThrows(InsufficientAuthenticationException.class,
            () -> filter.attemptAuthentication(request, new MockHttpServletResponse()));
    }

    @Test
    @DisplayName("인증 처리 정상완료")
    void attemptAuthentication_success() throws IOException {
        request.setContent("{\"username\":\"test\",\"password\":\"testPassword\"}".getBytes());
        filter.attemptAuthentication(request, new MockHttpServletResponse());
        final UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken("test", "testPassword");
        verify(authenticationManager).authenticate(token);
    }
}