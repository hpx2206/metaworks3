package org.uengine.codi.mw3.ide;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.uengine.kernel.GlobalContext;

public class DockerService implements IStorageService{

	@Override
	public boolean putObject(String projectId, String projectName, String version, boolean isProd)
			throws IOException {
		System.out.println("putObject...");
		String reopsitoryUrl = null;
		String codebase = GlobalContext.getPropertyString("codebase");
//		String targetFilePath = codebase + File.separatorChar + projectId + File.separatorChar
//				+ "maven" + File.separatorChar + projectName + "-" + version + ".jar";
		String uploadFilePath = codebase + File.separatorChar + projectId + File.separatorChar
				+ "maven" + File.separatorChar + projectName + "-" + version + ".jar";
		
		if(isProd){
			reopsitoryUrl = GlobalContext.getPropertyString("file.repository.url.prod");
		}else{
			reopsitoryUrl = GlobalContext.getPropertyString("file.repository.url.dev");
		}
		
		HttpPost httppost = new HttpPost(reopsitoryUrl);
	    
		File fileToUse = new File(uploadFilePath);
		FileBody data = new FileBody(fileToUse);
		
		
	    MultipartEntity requestEntity = new MultipartEntity();
	    requestEntity.addPart("data", data); 

	    httppost.setEntity(requestEntity);

	    HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse httpResponse = httpclient.execute(httppost);
	    HttpEntity resEntity = httpResponse.getEntity();
	    
	    // Get the HTTP Status Code
	    int statusCode = httpResponse.getStatusLine().getStatusCode();
	    boolean successed = false;
	    // Get the contents of the response
	    InputStream input = resEntity.getContent();
	    String responseBody = IOUtils.toString(input);
	    input.close();
	    
	    // Print the response code and message body
	    System.out.println("HTTP Status Code: "+statusCode);
	    System.out.println(responseBody);
	    
	    if("200".equals(String.valueOf(statusCode))){
	    	successed = true;
	    }
	    return successed;
	}

}
