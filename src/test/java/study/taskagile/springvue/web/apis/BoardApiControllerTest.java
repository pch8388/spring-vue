package study.taskagile.springvue.web.apis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import study.taskagile.springvue.domain.application.command.CreateBoardCommand;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.user.User;
import study.taskagile.springvue.infrastructure.repository.BoardRepository;
import study.taskagile.springvue.infrastructure.repository.UserRepository;
import study.taskagile.springvue.web.apis.authenticate.WithMockCustomUser;
import study.taskagile.springvue.web.payload.AddBoardMemberPayload;
import study.taskagile.springvue.web.payload.CreateBoardPayload;

import static java.util.Objects.requireNonNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static study.taskagile.springvue.utils.JsonUtils.toJson;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BoardApiControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private BoardRepository boardRepository;
    @Autowired private UserRepository userRepository;

    @Test
    @DisplayName("보드 저장")
    @WithMockCustomUser
    @Transactional
    void create() throws Exception {
        CreateBoardPayload payload = new CreateBoardPayload("board", "It's board!", 1L);
        final CreateBoardCommand command = payload.toCommand(2L);

        mockMvc.perform(post("/api/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requireNonNull(toJson(command))))
            .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("보드에 멤버 추가")
    @WithMockCustomUser
    @Transactional
    void add_member() throws Exception {
        final User saveUser = userRepository.save(
            User.create("test-user", "test@test.com", "123"));
        final Board saveBoard = boardRepository.save(
            Board.create(saveUser.getId(), "test-board", "test-description", null));

        final AddBoardMemberPayload payload = AddBoardMemberPayload.builder()
            .usernameOrEmailAddress(saveUser.getUsername()).build();

        mockMvc.perform(post("/api/boards/{boardId}/members", saveBoard.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requireNonNull(toJson(payload))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.response.id").value(saveUser.getId()))
            .andExpect(jsonPath("$.response.shortName").value(saveUser.getUsername()));
    }
}