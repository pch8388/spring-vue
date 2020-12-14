package study.taskagile.springvue.web.apis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import study.taskagile.springvue.domain.application.command.CreateBoardCommand;
import study.taskagile.springvue.domain.model.user.User;
import study.taskagile.springvue.utils.JsonUtils;
import study.taskagile.springvue.web.apis.authenticate.WithMockCustomUser;
import study.taskagile.springvue.web.payload.CreateBoardPayload;

import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class BoardApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardApiController boardApiController;


    @Test
    @DisplayName("보드 저장")
    @WithMockCustomUser
    void create() throws Exception {
        CreateBoardPayload payload = new CreateBoardPayload("board", "It's board!", 1L);
        final CreateBoardCommand command = payload.toCommand(2L);

        final User mock = mock(User.class);
        when(mock.getId()).thenReturn(1L);
        mockMvc.perform(post("/api/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(JsonUtils.toJson(command))))
            .andExpect(status().isCreated());

    }
}