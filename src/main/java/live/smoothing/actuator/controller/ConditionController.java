package live.smoothing.actuator.controller;

import live.smoothing.actuator.dto.ModifyTimoutConditionRequest;
import live.smoothing.actuator.dto.TimeoutResponse;
import live.smoothing.actuator.service.ConditionSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actuator/condition")
@RequiredArgsConstructor
public class ConditionController {

    private final ConditionSettingsService conditionSettingsService;

    @PutMapping("/timeout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyIlluminationTimout(@RequestBody ModifyTimoutConditionRequest request) {
        conditionSettingsService.modifyIlluminationTimeout(request);
    }

    @GetMapping("/timeout/{eui}")
    public TimeoutResponse getTimeout(@PathVariable String eui) {
        return conditionSettingsService.getTimeout(eui);
    }
}
