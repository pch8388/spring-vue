package study.taskagile.springvue.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import study.taskagile.springvue.domain.model.board.BoardMember;
import study.taskagile.springvue.domain.model.board.BoardMemberRepository;

public interface JpaBoardMemberRepository extends CrudRepository<BoardMember, Long>, BoardMemberRepository {
}
