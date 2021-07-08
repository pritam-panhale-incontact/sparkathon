package sparkathon.dashboard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sparkathon.dashboard.entities.Monitor;

public interface MonitorRepository extends JpaRepository<Monitor, Long> {
}
