package live.smoothing.actuator.service;

import live.smoothing.actuator.entity.ControlElement;
import live.smoothing.actuator.repository.ControlElementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ControlElementService {

    private final ControlElementRepository controlElementRepository;

    public ControlElement findByPlaceAndDeviceAndEvent(String place, String device, String event) {
        return controlElementRepository.findByPlaceAndDeviceAndEvent(place, device, event);
    }
}
