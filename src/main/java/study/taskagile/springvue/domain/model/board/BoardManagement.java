package study.taskagile.springvue.domain.model.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardManagement {

    private final BoardRepository boardRepository;
    private final BoardMemberRepository boardMemberRepository;

    public Board createBoard(
        Long creatorId, String name,
        String description, Long teamId) {

        Board board = Board.create(creatorId, name, description, teamId);
        boardRepository.save(board);

        BoardMember boardMember = BoardMember.create(board.getId(), creatorId);
        boardMemberRepository.save(boardMember);
        return board;
    }
}
