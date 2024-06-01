package live.smoothing.actuator.repository;

import live.smoothing.actuator.entity.ControlElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControlElementRepository extends JpaRepository<ControlElement, String> {

    ControlElement findByPlaceAndDeviceAndEvent(String place, String device, String event);
}
