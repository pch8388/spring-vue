package study.taskagile.springvue.web.socket;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ChannelHandler {
    String pattern() default "";
    String value() default "";
}
