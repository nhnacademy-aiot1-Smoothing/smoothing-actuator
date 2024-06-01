package live.smoothing.actuator.advice;

import live.smoothing.common.dto.ErrorResponse;
import live.smoothing.common.exception.CommonException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(CommonException.class)
    public ErrorResponse handleCommonException(HttpServletRequest request, HttpServletResponse response, CommonException e) {
        response.setStatus(e.getStatus().value());
        return e.toEntity(request.getServletPath());
    }
}
