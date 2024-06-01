package live.smoothing.actuator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * data에서 필요한 정보를 담는 DTO
 *
 * @author 신민석
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataDTO {
    private String location;
    private String device;
    private String event;
    private String value;
    private LocalDateTime time;
}