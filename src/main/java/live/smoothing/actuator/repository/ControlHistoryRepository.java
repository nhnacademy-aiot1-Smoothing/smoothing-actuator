package live.smoothing.actuator.repository;

import live.smoothing.actuator.entity.ControlHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlHistoryRepository extends JpaRepository<ControlHistory, Long> {

}
