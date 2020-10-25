package study.taskagile.springvue.domain.common.event;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
