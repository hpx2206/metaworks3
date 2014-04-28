package org.uengine.codi.mw3.ide;

import java.io.File;
import java.io.IOException;

import org.uengine.kernel.GlobalContext;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3PutObject {

	String bucketName    = null;
	String accessKey     = "AKIAIMSA24T5GYWMH5DQ";
	String secretKey     = "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";
			
	public void putObject(String uploadFilePath,String keyName, boolean isProd) throws IOException{
		if(isProd){
			bucketName = GlobalContext.getPropertyString("app.repo.prod");
		}else{
			bucketName = GlobalContext.getPropertyString("app.repo.dev");
		}
		
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        
		AmazonS3 s3client = new AmazonS3Client(awsCredentials);
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            File file = new File(uploadFilePath);
            s3client.putObject(new PutObjectRequest(bucketName, keyName, file));

         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
	}
}
