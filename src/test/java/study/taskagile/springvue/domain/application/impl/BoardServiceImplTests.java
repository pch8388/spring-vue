package study.taskagile.springvue.domain.application.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.taskagile.springvue.domain.application.command.CreateBoardCommand;
import study.taskagile.springvue.domain.common.event.DomainEventPublisher;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.board.BoardManagement;
import study.taskagile.springvue.domain.model.board.event.BoardCreateEvent;
import study.taskagile.springvue.infrastructure.repository.BoardRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class BoardServiceImplTests {

    @Mock
    private BoardRepository boardRepository;
    @Mock
    private BoardManagement boardManagement;
    @Mock
    private DomainEventPublisher domainEventPublisher;
    @InjectMocks
    private BoardServiceImpl boardService;

    @Test
    @DisplayName("파라미터가 null 이면 예외발생")
    void parameter_null_exception() {
        assertThrows(IllegalArgumentException.class,
            () -> boardService.createBoard(null));
    }

    @Test
    @DisplayName("보드 생성")
    void create() {
        CreateBoardCommand command = generatedCommand();
        Board newBoard = Board.create(
            command.getUserId(), command.getName(),
            command.getDescription(), command.getTeamId());

        when(boardManagement.createBoard(anyLong(), anyString(), anyString(), anyLong()))
            .thenReturn(newBoard);

        boardService.createBoard(command);

        verify(domainEventPublisher).publish(any(BoardCreateEvent.class));
    }

    private CreateBoardCommand generatedCommand() {
        Long userId = 1L;
        String name = "user 1 board";
        String description = "new board!!";
        Long teamId = 2L;
        return new CreateBoardCommand(userId, name, description, teamId);
    }
}