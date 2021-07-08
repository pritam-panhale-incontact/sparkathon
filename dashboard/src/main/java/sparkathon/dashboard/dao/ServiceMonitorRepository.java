package sparkathon.dashboard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sparkathon.dashboard.entities.ServiceMonitor;

public interface ServiceMonitorRepository extends JpaRepository<ServiceMonitor, Long> {
}
