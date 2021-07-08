package sparkathon.dashboard;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws Exception {
        AWSCredentialsProvider defaultAWSCredentialsProvider = new DefaultAWSCredentialsProviderChain();

        try {
            defaultAWSCredentialsProvider.getCredentials();
        } catch (AmazonClientException ex) {
            System.out.println("Can not load the credentials correctly, problem in creating AWS client.");
            throw new AmazonClientException("Can not load the credentials correctly, problem in creating AWS client", ex);
        }
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(defaultAWSCredentialsProvider)
                .withRegion(Regions.US_WEST_2)
                .build();

        boolean nirajdemotest = s3Client.doesBucketExistV2("nirajdemotest");
        System.out.println("--------------->" + nirajdemotest);
    }
}
