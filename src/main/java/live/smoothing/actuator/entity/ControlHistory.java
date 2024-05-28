package live.smoothing.actuator.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 디바이스 제어 이력을 위한 엔티티
 *
 * @autor 신민석
 */
@Getter
@Setter
@Entity
@Table(name = "control_history")
public class ControlHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String device;
    private String controlMessage;
    private LocalDateTime time;

}