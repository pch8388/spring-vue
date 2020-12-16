package study.taskagile.springvue.domain.model.user;

import study.taskagile.springvue.domain.common.model.BusinessException;

public class RegistrationException extends BusinessException {
    public RegistrationException(String message) {
        super(message);
    }
}
