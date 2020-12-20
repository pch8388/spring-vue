package study.taskagile.springvue.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.taskagile.springvue.domain.application.CardService;
import study.taskagile.springvue.domain.common.security.CurrentUser;
import study.taskagile.springvue.domain.model.card.Card;
import study.taskagile.springvue.domain.model.user.SimpleUser;
import study.taskagile.springvue.web.payload.AddCardPayload;
import study.taskagile.springvue.web.payload.ChangeCardPositionsPayload;
import study.taskagile.springvue.web.results.AddCardResponseDto;
import study.taskagile.springvue.web.results.ApiResult;
import study.taskagile.springvue.web.updater.CardUpdater;

import static study.taskagile.springvue.web.results.ApiResult.OK;

@RestController
@RequiredArgsConstructor
public class CardApiController {

    private final CardService cardService;
    private final CardUpdater cardUpdater;

    @PostMapping("/api/cards")
    public ApiResult<AddCardResponseDto> addCard(
        @RequestBody AddCardPayload payload,
        @CurrentUser SimpleUser currentUser) {

        final Card card = cardService.addCard(payload.toCommand(currentUser.getUserId()));
        cardUpdater.onCardAdded(payload.getBoardId(), card);
        return OK(new AddCardResponseDto(card));
    }

    @PostMapping("/api/card/positions")
    public ApiResult<?> changeCardPositions(@RequestBody ChangeCardPositionsPayload payload) {
        cardService.changePositions(payload.toCommand());
        return OK("success");
    }
}
