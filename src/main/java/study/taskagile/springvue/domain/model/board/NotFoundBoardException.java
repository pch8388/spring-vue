package study.taskagile.springvue.domain.model.board;

import study.taskagile.springvue.domain.common.model.BusinessException;

public class NotFoundBoardException extends BusinessException {
    public NotFoundBoardException(String message) {
        super(message);
    }
}
