package study.taskagile.springvue.web.apis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import study.taskagile.springvue.domain.application.command.AddCardCommand;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.cardList.CardList;
import study.taskagile.springvue.domain.model.user.User;
import study.taskagile.springvue.infrastructure.repository.BoardRepository;
import study.taskagile.springvue.infrastructure.repository.CardListRepository;
import study.taskagile.springvue.infrastructure.repository.UserRepository;
import study.taskagile.springvue.web.apis.authenticate.WithMockCustomUser;
import study.taskagile.springvue.web.payload.AddCardPayload;

import static java.util.Objects.requireNonNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static study.taskagile.springvue.utils.JsonUtils.toJson;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class CardApiControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private UserRepository userRepository;
    @Autowired private BoardRepository boardRepository;
    @Autowired private CardListRepository cardListRepository;

    private User saveUser;
    private Board saveBoard;
    private CardList saveCardList;

    @BeforeEach
    void setUp() {
        saveUser = userRepository.save(
            User.create("test-user", "test@test.com", "123"));
        saveBoard = boardRepository.save(
            Board.create(saveUser.getId(), "test-board", "test-description", null));

        final CardList cardList = CardList.create(
            saveBoard.getId(), saveUser.getId(),
            "test-card-list", 0);

        saveCardList = cardListRepository.save(cardList);
    }

    @Test
    @DisplayName("카드 추가")
    @WithMockCustomUser
    void addCard() throws Exception {
        final String cardTitle = "add card test";

        AddCardPayload payload = AddCardPayload.builder()
            .cardListId(saveCardList.getId())
            .boardId(saveBoard.getId())
            .title(cardTitle)
            .position(0)
            .build();

        final AddCardCommand command = payload.toCommand(saveUser.getId());

        mockMvc.perform(post("/api/cards")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requireNonNull(toJson(command))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.response.title").value(cardTitle));
    }
}