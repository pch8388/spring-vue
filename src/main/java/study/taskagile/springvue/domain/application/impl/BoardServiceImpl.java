package study.taskagile.springvue.domain.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import study.taskagile.springvue.domain.application.BoardService;
import study.taskagile.springvue.domain.application.command.CreateBoardCommand;
import study.taskagile.springvue.domain.common.event.DomainEventPublisher;
import study.taskagile.springvue.domain.model.board.event.BoardCreateEvent;
import study.taskagile.springvue.domain.model.board.BoardManagement;
import study.taskagile.springvue.domain.model.board.Board;
import study.taskagile.springvue.infrastructure.repository.BoardRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardManagement boardManagement;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public List<Board> findBoardsByMembership(Long userId) {
        return boardRepository.findBoardsByMembership(userId);
    }

    @Override
    public Board createBoard(CreateBoardCommand command) {
        Assert.notNull(command, "Parameter command must not be null");

        Board board = boardManagement.createBoard(
            command.getUserId(), command.getName(),
            command.getDescription(), command.getTeamId());

        domainEventPublisher.publish(new BoardCreateEvent(this, board));
        return board;
    }
}
