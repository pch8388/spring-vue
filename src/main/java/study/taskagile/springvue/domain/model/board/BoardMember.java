package study.taskagile.springvue.domain.model.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.taskagile.springvue.domain.common.model.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "board_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BoardMember extends BaseTimeEntity {

    @EmbeddedId
    private BoardMemberId id;

    public static BoardMember create(Long boardId, Long userId) {
        BoardMember boardMember = new BoardMember();
        boardMember.id = new BoardMemberId(boardId, userId);
        return boardMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardMember that = (BoardMember) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Embeddable
    @Getter
    public static class BoardMemberId implements Serializable {

        @Column(name = "board_id")
        private Long boardId;

        @Column(name = "user_id")
        private Long userId;

        public BoardMemberId() {
        }

        private BoardMemberId(Long boardId, Long userId) {
            this.boardId = boardId;
            this.userId = userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BoardMemberId that = (BoardMemberId) o;
            return Objects.equals(boardId, that.boardId) &&
                Objects.equals(userId, that.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(boardId, userId);
        }
    }
}
