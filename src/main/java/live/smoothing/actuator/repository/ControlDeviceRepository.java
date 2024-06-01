package live.smoothing.actuator.repository;

import live.smoothing.actuator.entity.ControlDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControlDeviceRepository extends JpaRepository<ControlDevice, String> {
}
