package study.taskagile.springvue.web.apis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configurers.openid.OpenIDLoginConfigurer;
import org.springframework.web.bind.annotation.*;
import study.taskagile.springvue.domain.application.BoardService;
import study.taskagile.springvue.domain.application.CardListService;
import study.taskagile.springvue.domain.application.CardService;
import study.taskagile.springvue.domain.application.TeamService;
import study.taskagile.springvue.domain.common.security.CurrentUser;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.card.Card;
import study.taskagile.springvue.domain.model.cardList.CardList;
import study.taskagile.springvue.domain.model.team.Team;
import study.taskagile.springvue.domain.model.user.RegistrationException;
import study.taskagile.springvue.domain.model.user.SimpleUser;
import study.taskagile.springvue.domain.model.user.User;
import study.taskagile.springvue.web.payload.AddBoardMemberPayload;
import study.taskagile.springvue.web.payload.CreateBoardPayload;
import study.taskagile.springvue.web.results.AddBoardMemberResponseDto;
import study.taskagile.springvue.web.results.ApiResult;
import study.taskagile.springvue.web.results.BoardResponseDto;
import study.taskagile.springvue.web.results.BoardSearchResponseDto;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static study.taskagile.springvue.utils.LoggerFormatUtils.API_ERROR_MESSAGE;
import static study.taskagile.springvue.web.results.ApiResult.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;
    private final TeamService teamService;
    private final CardListService cardListService;
    private final CardService cardService;

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
    public ApiResult<BoardSearchResponseDto> findBoard(@PathVariable Long boardId) {
        final Board board = boardService.findBoardById(boardId);
        final List<User> members = boardService.findMembers(boardId);
        final List<CardList> cardLists = cardListService.findByBoardId(boardId);
        final List<Card> cards = cardService.findByBoardId(boardId);

        return OK(new BoardSearchResponseDto(board, members, cardLists, cards, findTeam(board)));
    }

    private Team findTeam(Board board) {
        return board.isPersonal() ?
            null : teamService.findById(board.getTeamId());
    }

    @PostMapping("/api/boards/{boardId}/members")
    public ApiResult<AddBoardMemberResponseDto> addMembers(
        @PathVariable Long boardId,
        @RequestBody AddBoardMemberPayload payload) {

        final Board board = boardService.findBoardById(boardId);
        final User member = boardService.addMember(board.getId(), payload.getUsernameOrEmailAddress());
        return OK(new AddBoardMemberResponseDto(member));
    }
}
