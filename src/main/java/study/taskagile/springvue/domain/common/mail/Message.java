package study.taskagile.springvue.domain.common.mail;

public interface Message {
    String getTo();
    String getSubject();
    String getFrom();
    String getBody();
}
