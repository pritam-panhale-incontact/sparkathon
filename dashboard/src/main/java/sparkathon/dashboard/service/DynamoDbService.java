package sparkathon.dashboard.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sparkathon.dashboard.constants.Services;
import sparkathon.dashboard.dao.CommonDaoService;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;


@Service
public class DynamoDbService {

    @Value("${dynamodb.aws.endpoint}")
    private String endpoint;
    @Value("${dynamodb.aws.accesskey}")
    private String accessKey;
    @Value("${dynamodb.aws.secretkey}")
    private String secretKey;

    private String tableName = "Test_PersonDetails";

    private AmazonDynamoDB client;

    @Autowired
    private CommonDaoService commonDaoService;

    @PostConstruct
    public void init() {
        this.client = createAmazonDynamoDBClient();
    }

    public boolean checkDdbHealth() {

        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable(tableName);
        HashMap<String, String> nameMap = new HashMap<>();
        nameMap.put("#ID", "ID");

        HashMap<String, Object> valueMap = new HashMap<>();
        valueMap.put(":ID", "38ece59a-c64c-40ef-bea5-bd4a4f90a29c");

        QuerySpec querySpec = new QuerySpec()
                .withKeyConditionExpression("#ID = :ID")
                .withNameMap(nameMap)
                .withValueMap(valueMap);

        try {
            ItemCollection<QueryOutcome> items = table.query(querySpec);

            Iterator<Item> iterator = items.iterator();
            if (!iterator.hasNext()) {
                throw new RuntimeException("No result found");
            } else {
                commonDaoService.updateMetricDb(Services.DYNAMODB, true);
                return true;
            }
        } catch (Exception e) {
            System.err.println("Unable to query to DDB");
            System.err.println(e.getMessage());
            commonDaoService.updateMetricDb(Services.DYNAMODB, false);
            return false;
        }
    }

    private AmazonDynamoDB createAmazonDynamoDBClient() {
        if (this.endpoint.equals("http://localhost:8000/")) {
            BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
            return AmazonDynamoDBClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
        } else {
            return AmazonDynamoDBClientBuilder.standard().build();
        }
    }
}
