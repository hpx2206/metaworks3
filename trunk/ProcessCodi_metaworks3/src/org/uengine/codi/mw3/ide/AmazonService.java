package org.uengine.codi.mw3.ide;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IStorage;
import org.uengine.kernel.GlobalContext;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class AmazonService implements IStorageService{

	String bucketName    = null;
	String accessKey     = "AKIAIMSA24T5GYWMH5DQ";
	String secretKey     = "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";
			
	public boolean putObject(String projectId ,String projectName, boolean isProd) throws IOException{
		
		System.out.println("putObject...");
		String codebase = GlobalContext.getPropertyString("codebase");
		String uploadFilePath = codebase + File.separatorChar + projectId + File.separatorChar
				+ "maven" + File.separatorChar + projectName+".jar";
		
		if(isProd){
			bucketName = GlobalContext.getPropertyString("app.repo.prod");
		}else{
			bucketName = GlobalContext.getPropertyString("app.repo.dev");
		}
		
		boolean successed = false;
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        
		AmazonS3 s3client = new AmazonS3Client(awsCredentials);
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            File file = new File(uploadFilePath);
            s3client.putObject(new PutObjectRequest(bucketName, projectName+".jar", file));
            successed = true;
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
        
        return successed;
	}
}
