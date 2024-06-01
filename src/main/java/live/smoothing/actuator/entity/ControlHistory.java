package live.smoothing.actuator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 디바이스 제어 이력을 위한 엔티티
 *
 * @autor 박영준, 신민석
 */
@Getter
@Entity
@Table(name = "control_history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ControlHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eui;

    private LocalDateTime time;

    private String message;
}