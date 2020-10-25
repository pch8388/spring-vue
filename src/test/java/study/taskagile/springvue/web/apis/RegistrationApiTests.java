package study.taskagile.springvue.web.apis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import study.taskagile.springvue.domain.application.UserService;
import study.taskagile.springvue.utils.JsonUtils;
import study.taskagile.springvue.web.payload.RegistrationPayload;

import java.util.Objects;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationApiTests {
    @Autowired private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("빈 payload 요청시 - 400 리턴")
    void register_blankPayload_400() throws Exception {
        mvc.perform(post("/api/registrations"))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("가입 성공 - 201 리턴")
    void register() throws Exception {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("exist");
        payload.setEmailAddress("test@taskagile.com");
        payload.setPassword("MyPassword!");

        mvc.perform(post("/api/registrations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(Objects.requireNonNull(JsonUtils.toJson(payload))))
            .andExpect(status().isCreated());

        verify(userService).register(any());
    }
}