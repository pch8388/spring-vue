package study.taskagile.springvue.web.socket;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String value() default "";
}
