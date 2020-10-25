package study.taskagile.springvue.web.results;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ApiError {

    private final String message;
    private final int status;

    ApiError(Throwable throwable, HttpStatus httpStatus) {
        this(throwable.getMessage(), httpStatus);
    }

    ApiError(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus.value();
    }
}
