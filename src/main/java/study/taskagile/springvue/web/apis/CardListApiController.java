package study.taskagile.springvue.web.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.taskagile.springvue.domain.application.CardListService;
import study.taskagile.springvue.domain.common.security.CurrentUser;
import study.taskagile.springvue.domain.model.user.SimpleUser;
import study.taskagile.springvue.web.payload.AddCardListPayload;
import study.taskagile.springvue.web.payload.ChangeCardListPositionsPayload;
import study.taskagile.springvue.web.results.AddCardListResponseDto;
import study.taskagile.springvue.web.results.ApiResult;

import static study.taskagile.springvue.web.results.ApiResult.OK;

@RestController
@RequiredArgsConstructor
public class CardListApiController {

    private final CardListService cardListService;

    @PostMapping("/api/card-lists")
    public ApiResult<AddCardListResponseDto> addCardList(
        @RequestBody AddCardListPayload payload,
        @CurrentUser SimpleUser currentUser) {

        return OK(new AddCardListResponseDto(
            cardListService.addCardList(
                payload.toCommand(currentUser.getUserId())))
        );
    }

    @PostMapping("/api/card-lists/positions")
    public ApiResult<String> changeCardListPositions(
        @RequestBody ChangeCardListPositionsPayload payload) {
        cardListService.changePositions(payload.toCommand());
        return OK("success");
    }
}
