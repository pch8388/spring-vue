package study.taskagile.springvue.domain.model.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.taskagile.springvue.infrastructure.repository.BoardMemberRepository;
import study.taskagile.springvue.infrastructure.repository.BoardRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class BoardManagementTest {

    @Mock
    private BoardRepository boardRepository;
    @Mock
    private BoardMemberRepository boardMemberRepository;
    @InjectMocks
    private BoardManagement boardManagement;

    @ParameterizedTest
    @DisplayName("board 생성 - 팀이 있는 경우와 없는 경우 확인")
    @ValueSource(longs = {2L, 0L})
    void create_board(long teamId) {
        Long creatorId = 1L;
        String name = "new Board";
        String description = "new board create";

        Board board = boardManagement.createBoard(
            creatorId, name, description, teamId);

        verify(boardRepository).save(any(Board.class));
        verify(boardMemberRepository).save(any(BoardMember.class));

        assertEquals(creatorId, board.getUserId());
        assertEquals(name, board.getName());
        assertEquals(description, board.getDescription());
        assertEquals(teamId, board.getTeamId());
    }

    @Test
    @DisplayName("필수 파라미터를 넘기지 않으면 예외 발생")
    void create_board_parameter_null_exception() {
        Long creatorId = 1L;
        String name = "new Board";
        String description = "new board create";
        Long teamId = 2L;

        assertThrows(IllegalArgumentException.class,
            () -> boardManagement.createBoard(
                null, name, description, teamId));

        assertThrows(IllegalArgumentException.class,
            () -> boardManagement.createBoard(
                creatorId, null, description, teamId));

        assertThrows(IllegalArgumentException.class,
            () -> boardManagement.createBoard(
                creatorId, name, null, teamId));

        assertThrows(IllegalArgumentException.class,
            () -> boardManagement.createBoard(
                creatorId, name, description, null));
    }
}