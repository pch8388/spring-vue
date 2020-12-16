package study.taskagile.springvue.domain.common.model;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
