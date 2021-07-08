package sparkathon.dashboard.service;


import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sparkathon.dashboard.constants.Services;
import sparkathon.dashboard.dao.CommonDaoService;

import javax.annotation.PostConstruct;

@Service
public class S3Service {

    private AmazonS3 s3Client;

    private static final String BUCKET_NAME = "nirajdemotest";

    @Autowired
    private CommonDaoService commonDaoService;

    @PostConstruct
    public void init() {
        AWSCredentialsProvider defaultAWSCredentialsProvider = new DefaultAWSCredentialsProviderChain();

        try {
            defaultAWSCredentialsProvider.getCredentials();
        } catch (AmazonClientException ex) {
            System.out.println("Can not load the credentials correctly, problem in creating AWS client.");
            throw new AmazonClientException("Can not load the credentials correctly, problem in creating AWS client", ex);
        }
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(defaultAWSCredentialsProvider)
                .withRegion(Regions.US_WEST_2)
                .build();
    }

    public void checkS3Health() {
        try {
            boolean nirajdemotest = s3Client.doesBucketExistV2(BUCKET_NAME);
            if (nirajdemotest) {
                commonDaoService.updateMetricDb(Services.S3, true);
            } else {
                commonDaoService.updateMetricDb(Services.S3, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonDaoService.updateMetricDb(Services.S3, false);
        }

    }
}
