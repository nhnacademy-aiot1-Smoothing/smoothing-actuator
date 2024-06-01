package live.smoothing.actuator.exception;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class NotManualException extends CommonException {
    public NotManualException(HttpStatus status, String errorMessage) {
        super(status, errorMessage);
    }
}
