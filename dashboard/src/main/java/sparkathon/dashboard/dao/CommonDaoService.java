package sparkathon.dashboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sparkathon.dashboard.constants.Services;
import sparkathon.dashboard.entities.ServiceMonitor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class CommonDaoService {

    @Autowired
    private ServiceMonitorRepository serviceMonitorRepository;

    public void updateMetricDb(Services services, boolean flag) {
        Integer status;
        if (flag) {
            System.out.println(services.toString() + " is Active");
            status = 1;
        } else {
            System.out.println(services.toString() + " is Inactive");
            status = 0;
        }

        ServiceMonitor serviceMonitor = new ServiceMonitor();
        serviceMonitor.setStatus(status);
        serviceMonitor.setServiceName(services.toString());
        serviceMonitor.setTimestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());
        serviceMonitorRepository.save(serviceMonitor);
    }

}
