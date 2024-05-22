package live.smoothing.smoothingactuator.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DataDTO {
    private String device;
    private String value;
    private String location;
    private LocalDateTime time;
}
