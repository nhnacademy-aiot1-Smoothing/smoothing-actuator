package live.smoothing.actuator.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String device;
    private String value;
    private String location;
    private LocalDateTime time;

//    @JsonCreator
//    public DataDTO(
//            @JsonProperty("location") String location,
//            @JsonProperty("time") LocalDateTime time,
//            @JsonProperty("device") String device,
//            @JsonProperty("value") String value) {
//        this.location = location;
//        this.time = time;
//        this.device = device;
//        this.value = value;
//    }
}