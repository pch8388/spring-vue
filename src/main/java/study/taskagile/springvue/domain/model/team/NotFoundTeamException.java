package study.taskagile.springvue.domain.model.team;

import study.taskagile.springvue.domain.common.model.BusinessException;

public class NotFoundTeamException extends BusinessException {
    public NotFoundTeamException(String message) {
        super(message);
    }
}
