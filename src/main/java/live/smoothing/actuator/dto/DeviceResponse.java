package live.smoothing.actuator.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DeviceResponse {
    private List<DeviceDTO> deviceDTOLIst;
}
