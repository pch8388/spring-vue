package study.taskagile.springvue.web.results;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class BoardSearchResponseDto {

    private final TeamDto team;
    private final BoardDto board;
    private final List<MemberDto> members;
    private final List<CardListDto> cardLists;

    public BoardSearchResponseDto create() {
        return new BoardSearchResponseDto(team, board, members, cardLists);
    }

    @Getter
    public static class TeamDto {
        private String name;
    }

    @Getter
    public static class BoardDto {
        private String name;
        private boolean personal;
        private Long id;
    }

    @Getter
    public static class MemberDto {
        private Long userId;
        private String shortName;
    }

    @Getter
    public static class CardListDto {
        private Long id;
        private String name;
        private int position;
        private List<CardDto> cards;

        @Getter
        public static class CardDto {
            private Long id;
            private String title;
            private int position;
        }
    }
}
