package sparkathon.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sparkathon.dashboard.dao.MonitorRepository;
import sparkathon.dashboard.entities.Monitor;

import java.util.List;


@Service
public class RDSDbService {

    @Autowired
    private MonitorRepository monitorRepository;

    public boolean checkDdbHealth() {
        try {
            List<Monitor> all = monitorRepository.findAll();
            if (all != null && !all.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
