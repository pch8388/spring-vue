package study.taskagile.springvue.web.apis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import study.taskagile.springvue.domain.application.BoardService;
import study.taskagile.springvue.domain.common.security.CurrentUser;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.user.RegistrationException;
import study.taskagile.springvue.domain.model.user.SimpleUser;
import study.taskagile.springvue.web.payload.CreateBoardPayload;
import study.taskagile.springvue.web.results.ApiResult;
import study.taskagile.springvue.web.results.BoardResponseDto;
import study.taskagile.springvue.web.results.BoardSearchResponseDto;

import javax.validation.Valid;
import java.util.Optional;

import static study.taskagile.springvue.utils.LoggerFormatUtils.API_ERROR_MESSAGE;
import static study.taskagile.springvue.web.results.ApiResult.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/boards")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<BoardResponseDto> createBoard(
        @Valid @RequestBody CreateBoardPayload payload,
        @CurrentUser SimpleUser currentUser) {

        Board board = boardService.createBoard(payload.toCommand(currentUser.getUserId()));
        return OK(
            Optional.ofNullable(board)
                .map(BoardResponseDto::entityToDto)
                .orElseThrow(() -> {
                    log.error(API_ERROR_MESSAGE, this.getClass().getName(), "createBoard", "register");
                    return new RegistrationException("board register api exception!");
                })
        );
    }

    @GetMapping("/api/boards/{boardId}")
    public ApiResult<BoardSearchResponseDto> search(
        @PathVariable Long boardId,
        @CurrentUser SimpleUser currentUser) {
        return OK(null);
    }
}
