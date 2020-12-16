package study.taskagile.springvue.web.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.card.Card;
import study.taskagile.springvue.domain.model.cardList.CardList;
import study.taskagile.springvue.domain.model.team.Team;
import study.taskagile.springvue.domain.model.user.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Getter
public class BoardSearchResponseDto {

    private final TeamDto team;
    private final BoardDto board;
    private final List<MemberDto> members;
    private final List<CardListDto> cardLists;

    public BoardSearchResponseDto(
        Board board, List<User> members,
        List<CardList> cardLists, List<Card> cards,
        Team team) {

        this.board = new BoardDto(board.getName(), board.isPersonal(), board.getId());
        this.team = Optional.ofNullable(team)
                            .map(Team::getName)
                            .map(TeamDto::new)
                            .orElse(null);

        this.members = members.stream()
            .map(MemberDto::new)
            .collect(toList());

        final Map<Long, List<Card>> cardMap = cards.stream()
            .collect(groupingBy(Card::getCardListId));

        this.cardLists = cardLists.stream()
            .map(cardList -> new CardListDto(cardList, cardMap.get(cardList.getId())))
            .collect(toList());
    }

    public BoardSearchResponseDto create() {
        return new BoardSearchResponseDto(team, board, members, cardLists);
    }

    @Getter
    @AllArgsConstructor
    public static class TeamDto {
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class BoardDto {
        private String name;
        private boolean personal;
        private Long id;
    }

    @Getter
    public static class MemberDto {
        private Long userId;
        private String shortName;

        private MemberDto(User user) {
            this.userId = user.getId();
            this.shortName = user.getUsername();
        }
    }

    @Getter
    public static class CardListDto {
        private Long id;
        private String name;
        private int position;
        private List<CardDto> cards;

        private CardListDto(CardList cardList, List<Card> cards) {
            this.id = cardList.getId();
            this.name = cardList.getName();
            this.position = cardList.getPosition();
            this.cards = cards.stream().map(CardDto::new).collect(toList());
        }

        @Getter
        public static class CardDto {
            private Long id;
            private String title;
            private int position;

            private CardDto(Card card) {
                this.id = card.getId();
                this.title = card.getTitle();
                this.position = card.getPosition();
            }
        }
    }
}
