package sparkathon.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sparkathon.dashboard.constants.Services;
import sparkathon.dashboard.dao.CommonDaoService;

@Service
public class RdsService {

    @Autowired
    private CommonDaoService commonDaoService;

    private RestTemplate restTemplate = new RestTemplate();

    public void checkRdsHealth() {
        try{
            Boolean response = this.restTemplate.getForObject("http://localhost:8081/dashboard/rds/status" , Boolean.class);
            if (response!=null && response) {
                commonDaoService.updateMetricDb(Services.RDS, true);
            } else {
                commonDaoService.updateMetricDb(Services.RDS, false);
            }
        }catch (Exception e) {
            e.printStackTrace();
            commonDaoService.updateMetricDb(Services.RDS, false);
        }

    }
}
