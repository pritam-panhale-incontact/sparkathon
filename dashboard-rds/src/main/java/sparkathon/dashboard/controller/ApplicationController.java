package sparkathon.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sparkathon.dashboard.service.RDSDbService;

@RestController
@RequestMapping("/dashboard")
public class ApplicationController {

    @Autowired
    private RDSDbService rdsDbService;

    @RequestMapping(value = "/rds/status", method = RequestMethod.GET)
    public boolean getDashboard() {
        System.out.println("Monitoring RDS health");
        return rdsDbService.checkDdbHealth();
    }
}
