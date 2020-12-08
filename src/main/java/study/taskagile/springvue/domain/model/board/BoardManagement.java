package study.taskagile.springvue.domain.model.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import study.taskagile.springvue.infrastructure.repository.BoardMemberRepository;
import study.taskagile.springvue.infrastructure.repository.BoardRepository;

@Component
@RequiredArgsConstructor
public class BoardManagement {

    private final BoardRepository boardRepository;
    private final BoardMemberRepository boardMemberRepository;

    public Board createBoard(
        Long creatorId, String name,
        String description, Long teamId) {

        Assert.notNull(creatorId, "Parameter creatorId must not be null");
        Assert.notNull(name, "Parameter name must not be null");
        Assert.notNull(description, "Parameter description must not be null");
        Assert.notNull(teamId, "Parameter teamId must not be null");

        Board board = Board.create(creatorId, name, description, teamId);
        boardRepository.save(board);

        BoardMember boardMember = BoardMember.create(board.getId(), creatorId);
        boardMemberRepository.save(boardMember);
        return board;
    }
}
