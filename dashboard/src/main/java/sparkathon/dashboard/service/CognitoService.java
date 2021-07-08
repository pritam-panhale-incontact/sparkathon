package sparkathon.dashboard.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.ListUserPoolsRequest;
import com.amazonaws.services.cognitoidp.model.ListUserPoolsResult;
import com.amazonaws.services.cognitoidp.model.UserPoolDescriptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sparkathon.dashboard.constants.Services;
import sparkathon.dashboard.dao.CommonDaoService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CognitoService {

    private AWSCognitoIdentityProvider awsCognitoIdentityProvider;

    private ListUserPoolsRequest listUserPoolsRequest = new ListUserPoolsRequest();

    @Autowired
    private CommonDaoService commonDaoService;

    @PostConstruct
    public void init() {

        this.listUserPoolsRequest.setMaxResults(1);
        this.awsCognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder.standard().withRegion("us-west-2").build();
    }

    public void monitorCognitoHeath() {
        try {
            ListUserPoolsResult listUserPoolsResult = this.awsCognitoIdentityProvider.listUserPools(this.listUserPoolsRequest);
            List<UserPoolDescriptionType> userPools = listUserPoolsResult.getUserPools();
            if (userPools != null && !userPools.isEmpty()) {
                commonDaoService.updateMetricDb(Services.COGNITO, true);
            } else {
                commonDaoService.updateMetricDb(Services.COGNITO, false);
            }
        } catch (Exception e) {
            System.err.println("Not able to list userpools from Cognito");
            commonDaoService.updateMetricDb(Services.COGNITO, false);
        }
    }
}
