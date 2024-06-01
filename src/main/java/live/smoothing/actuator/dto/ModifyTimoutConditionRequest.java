package live.smoothing.actuator.dto;

import lombok.Getter;

@Getter
public class ModifyTimoutConditionRequest {
    String eui;
    Long timeout;
}