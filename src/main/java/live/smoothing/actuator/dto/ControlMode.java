package live.smoothing.actuator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ControlMode {

    private String place;
    private String device;
    private String mode;
}
