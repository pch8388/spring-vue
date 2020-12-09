package study.taskagile.springvue.infrastructure.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.domain.model.board.BoardMember;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardMemberRepository boardMemberRepository;

    private static final long USER_ID = 1L;

    @Test
    @DisplayName("유저가 소속된 보드 찾기")
    void find_boards_member() {
        IntStream.range(0, 10)
            .forEach(i -> {
                Board board = boardRepository.save(
                    Board.create(USER_ID, "new board - " + i, "new board create!!", 0L));
                boardMemberRepository.save(
                    BoardMember.create(board.getId(), USER_ID));
            });

        final List<Board> boards = boardRepository.findBoardsByMembership(USER_ID);
        assertEquals(boards.size(), 10);
    }
}