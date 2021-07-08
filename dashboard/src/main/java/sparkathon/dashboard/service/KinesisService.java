package sparkathon.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sparkathon.dashboard.constants.Services;
import sparkathon.dashboard.dao.CommonDaoService;

import java.io.BufferedReader;
import java.io.InputStreamReader;


@Service
public class KinesisService {

    @Autowired
    private CommonDaoService commonDaoService;

    public void checkStreamStatus() {
        Process p;
        try {
            p = Runtime.getRuntime().exec("aws kinesis describe-stream --stream-name sparkathon");

            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            boolean res = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("StreamStatus") && line.contains("ACTIVE")) {
                    res = true;
                    break;
                }
            }
            if (res) {
                commonDaoService.updateMetricDb(Services.KINESIS, true);
            } else {
                commonDaoService.updateMetricDb(Services.KINESIS, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonDaoService.updateMetricDb(Services.KINESIS, false);
        }
    }
}
