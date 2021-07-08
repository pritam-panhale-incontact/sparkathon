package sparkathon.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sparkathon.dashboard.service.CognitoService;
import sparkathon.dashboard.service.DynamoDbService;
import sparkathon.dashboard.service.KinesisService;

@Service
@EnableScheduling
public class MonitorService {

    @Autowired
    private DynamoDbService dynamoDbService;

    @Autowired
    private CognitoService cognitoService;

    @Autowired
    private KinesisService kinesisService;

    @Scheduled(fixedRate = 60000)
    public void ddbServiceScheduler() {
        try {
            monitorDDBService();
            monitorKinesisService();
            monitorCognitoService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void monitorDDBService() throws InterruptedException {
        System.out.println("Monitoring DDB health");
        dynamoDbService.checkDdbHealth();
    }

    @Async
    public void monitorKinesisService() {
        System.out.println("Monitoring Kinesis health");
        kinesisService.checkStreamStatus();
    }

    @Async
    public void monitorCognitoService() {
        System.out.println("Monitoring Cognito health");
        cognitoService.monitorCognitoHeath();
    }
}
