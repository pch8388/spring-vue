package study.taskagile.springvue.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import study.taskagile.springvue.domain.model.board.BoardMember;

public interface BoardMemberRepository extends CrudRepository<BoardMember, Long> {
}
