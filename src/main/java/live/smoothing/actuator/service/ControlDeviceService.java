package live.smoothing.actuator.service;

import live.smoothing.actuator.entity.ControlDevice;
import live.smoothing.actuator.repository.ControlDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ControlDeviceService {

    private final ControlDeviceRepository controlDeviceRepository;

    public ControlDevice findByEui(String eui) {
        return controlDeviceRepository.findById(eui)
                .orElseThrow(() -> new IllegalArgumentException("해당 EUI를 찾을 수 없습니다."));
    }
}
