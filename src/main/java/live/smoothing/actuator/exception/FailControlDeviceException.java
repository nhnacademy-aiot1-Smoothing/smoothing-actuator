package live.smoothing.actuator.exception;


import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class FailControlDeviceException extends CommonException {
    public FailControlDeviceException(HttpStatus status, String errorMessage) {
        super(status, errorMessage);
    }
}
