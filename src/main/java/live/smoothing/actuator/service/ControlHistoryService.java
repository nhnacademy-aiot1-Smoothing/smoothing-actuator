package live.smoothing.actuator.service;

import live.smoothing.actuator.dto.HistoryDTO;
import live.smoothing.actuator.entity.ControlHistory;
import live.smoothing.actuator.repository.ControlHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        ControlHistory history = ControlHistory.builder()
                .eui(device)
                .time(LocalDateTime.now())
                .message(controlMessage)
                .build();

        repository.save(history);
    }

    public List<HistoryDTO> getHistory() {
        List<ControlHistory> histories = repository.findAll();

        return histories.stream()
                .map(history -> new HistoryDTO(history.getEui(), history.getTime().toString(), history.getMessage()))
                .collect(Collectors.toList());
    }
}