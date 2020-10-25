package study.taskagile.springvue.web.results;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ApiResult<T> {
    private final T response;
    private final ApiError error;

    private ApiResult(T response, ApiError error) {
        this.response = response;
        this.error = error;
    }

    public static <T> ApiResult<T> OK(T response) {
        return new ApiResult<>(response, null);
    }

    public static <T> ApiResult<T> ERROR(Throwable throwable, HttpStatus status) {
        return new ApiResult<>(null, new ApiError(throwable, status));
    }

    public static <T> ApiResult<T> ERROR(String errorMessage, HttpStatus status) {
        return new ApiResult<>(null, new ApiError(errorMessage, status));
    }
}
