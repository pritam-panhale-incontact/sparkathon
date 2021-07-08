package sparkathon.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sparkathon.dashboard.service.*;

import java.time.LocalDateTime;

@Service
@EnableScheduling
public class MonitorService {

    @Autowired
    private DynamoDbService dynamoDbService;

    @Autowired
    private CognitoService cognitoService;

    @Autowired
    private KinesisService kinesisService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private RdsService rdsService;

    @Scheduled(fixedRate = 60000)
    public void ddbServiceScheduler() {
        System.out.println("------------------------------------------------");
        System.out.println("Monitoring Started at : " + LocalDateTime.now());
        try {
            monitorDDBService();
            monitorKinesisService();
            monitorCognitoService();
            monitors3Service();
            monitorRdsService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------------------");
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

    @Async
    public void monitors3Service() {
        System.out.println("Monitoring S3 health");
        s3Service.checkS3Health();
    }

    @Async
    public void monitorRdsService() {
        System.out.println("Monitoring RDS health");
        rdsService.checkRdsHealth();
    }
}
