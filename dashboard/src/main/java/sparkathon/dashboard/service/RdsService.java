package sparkathon.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sparkathon.dashboard.constants.Services;
import sparkathon.dashboard.dao.CommonDaoService;

@Service
public class RdsService {

    @Autowired
    private CommonDaoService commonDaoService;

    public void checkRdsHealth(boolean status) {
        commonDaoService.updateMetricDb(Services.KINESIS, true);
    }
}
