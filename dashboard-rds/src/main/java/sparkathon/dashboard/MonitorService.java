package sparkathon.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sparkathon.dashboard.service.RDSDbService;

@Service
@EnableScheduling
public class MonitorService {

    @Autowired
    private RDSDbService rdsDbService;

    @Scheduled(fixedRate = 60000)
    public void ddbServiceScheduler() {
        try {
            monitorRDSService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void monitorRDSService() throws InterruptedException {
        System.out.println("Monitoring RDS health");
        rdsDbService.checkDdbHealth();
    }

}
