package sparkathon.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sparkathon.dashboard.dao.MonitorRepository;
import sparkathon.dashboard.entities.Monitor;

import java.util.List;


@Service
public class RDSDbService {

    @Value("${dynamodb.aws.accesskey}")
    private String accessKey;
    @Value("${dynamodb.aws.secretkey}")
    private String secretKey;
    @Value("${dynamodb.aws.region:us-west-2}")
    private String region;

    @Autowired
    private MonitorRepository monitorRepository;

    private RestTemplate restTemplate = new RestTemplate();

    public boolean checkDdbHealth() {
        List<Monitor> all = monitorRepository.findAll();
        if (all != null && !all.isEmpty()) {
            updateHealthCheckApi(true);
        } else {
            updateHealthCheckApi(false);
        }
        return true;
    }

    private void updateHealthCheckApi(boolean status) {
        String statusValue = status ? "true" : "false";
        Boolean response = this.restTemplate.postForObject("http://localhost:8080/dashboard/rds/status?status=" + statusValue + "", null, Boolean.class);
        if (response) {
            System.out.println("RDS health check data successfully updated");
        } else {
            System.out.println("RDS health check not data updated");
        }
    }


}
