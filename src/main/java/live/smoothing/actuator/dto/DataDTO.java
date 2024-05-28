package live.smoothing.actuator.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * data에서 필요한 정보를 담는 DTO
 *
 * @author 신민석
 */
@Getter
@Setter
public class DataDTO {
    private String device;
    private String value;
    private String location;
    private LocalDateTime time;
}
