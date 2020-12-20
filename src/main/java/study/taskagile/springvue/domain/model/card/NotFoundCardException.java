package study.taskagile.springvue.domain.model.card;

import study.taskagile.springvue.domain.common.model.BusinessException;

public class NotFoundCardException extends BusinessException {

    private static final String MESSAGE = "Not found card id : ";
    public NotFoundCardException(Long cardId) {
        super(MESSAGE + cardId);
    }
}
