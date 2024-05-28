package live.smoothing.actuator.service;

import live.smoothing.actuator.entity.ControlHistory;
import live.smoothing.actuator.repository.ControlHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 제어 이력 서비스
 *
 * @author 신민석
 */
@Service
@AllArgsConstructor
public class ControlHistoryService {

    private final ControlHistoryRepository repository;

    public void save(String device, String controlMessage) {
        ControlHistory history = new ControlHistory();
        history.setDevice(device);
        history.setControlMessage(controlMessage);
        history.setTime(LocalDateTime.now());

        repository.save(history);
    }


}
