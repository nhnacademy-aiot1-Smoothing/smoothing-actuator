package live.smoothing.actuator.controller;

import live.smoothing.actuator.dto.HistoryResponse;
import live.smoothing.actuator.service.ControlHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actuator")
@RequiredArgsConstructor
public class HistoryController {

    private final ControlHistoryService controlHistoryService;

    @GetMapping("/history")
    public HistoryResponse getHistory() {
        return new HistoryResponse(controlHistoryService.getHistory());
    }
}