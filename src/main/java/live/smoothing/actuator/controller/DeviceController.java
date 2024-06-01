package live.smoothing.actuator.controller;

import live.smoothing.actuator.dto.ControlModeResponse;
import live.smoothing.actuator.dto.ControlRequest;
import live.smoothing.actuator.dto.DeviceDTO;
import live.smoothing.actuator.dto.DeviceResponse;
import live.smoothing.actuator.service.DeviceControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actuator/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceControlService deviceControlService;

    @PostMapping("/turn")
    public void turnDevice(@RequestBody ControlRequest request) {
        deviceControlService.turnDevice(request.getEui(), request.getAction());
    }

    @PostMapping("/control/turn")
    public void turnControl(@RequestBody ControlRequest request) {
        deviceControlService.turnControl(request.getEui(), request.getAction());
    }

    @GetMapping
    public DeviceResponse getDevices() {
        List<DeviceDTO> devices = deviceControlService.getDevices();
        return new DeviceResponse(devices);
    }
}
