package sparkathon.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sparkathon.dashboard.constants.Services;
import sparkathon.dashboard.dao.CommonDaoService;

@RestController
@RequestMapping("/dashboard")
public class ApplicationController {

    @Autowired
    private CommonDaoService commonDaoService;

    @RequestMapping(value = "/rds/status", method = RequestMethod.POST)
    public boolean getDashboard(@RequestParam boolean status) {
        System.out.println("Monitoring RDS health");
        commonDaoService.updateMetricDb(Services.RDS, true);
        return true;
    }
}
