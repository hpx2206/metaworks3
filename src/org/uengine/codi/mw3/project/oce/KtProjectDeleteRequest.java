package org.uengine.codi.mw3.project.oce;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.metaworks.dao.ConnectionFactory;
import org.metaworks.dao.JDBCConnectionFactory;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.util.Base64;
import org.uengine.kernel.GlobalContext;

public class KtProjectDeleteRequest {
	
	CloudInfo cloudInfo;
		public CloudInfo getCloudInfo() {
			return cloudInfo;
		}
		public void setCloudInfo(CloudInfo cloudInfo) {
			this.cloudInfo = cloudInfo;
		}
		
	public static ConnectionFactory connectionFactory;
	
	public KtProjectDeleteRequest(){
		
	}
	
	public void deleteRequest() throws Exception{
		if( cloudInfo != null ){
			
			final String vm_id= cloudInfo.getServerId();
			final String ip_id= cloudInfo.getServerIpId();
			
			final CloudInfo cloudInfoFinal = cloudInfo;
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						
						// ip delete
						String command = "disassociateIpAddress&id="+ip_id;		
						cmdExec(command);
						
						// vm stop
						command = "stopVirtualMachine&id=" + vm_id;
						cmdExec(command);
						
						// vm delete
						command = "destroyVirtualMachine&id="+vm_id;	
						cmdExec(command);	
						
						TransactionContext tx = new TransactionContext(); //once a TransactionContext is created, it would be cached by ThreadLocal.set, so, we need to remove this after the request processing. 
						try{
							tx.setManagedTransaction(false);
							tx.setAutoCloseConnection(true);
							
							String connectionString = GlobalContext.getPropertyString("jdbc.url", null);
							if(connectionString!=null){
								String driverClass = GlobalContext.getPropertyString("jdbc.driverClassName", null);
								String userId = GlobalContext.getPropertyString("jdbc.username", "root");
								String password = GlobalContext.getPropertyString("jdbc.password", "root");
								
								JDBCConnectionFactory cf = new JDBCConnectionFactory();
								cf.setConnectionString(connectionString);
								cf.setDriverClass(driverClass);
								cf.setUserId(userId);
								cf.setPassword(password);
								connectionFactory = cf;
							}
							if(connectionFactory!=null){
								tx.setConnectionFactory(connectionFactory);
							}
							
							cloudInfoFinal.deleteDatabaseMe();
							tx.commit();
						}catch(Exception e){
							tx.rollback();
							throw e;
						}finally{
							tx.releaseResources();
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			}).start();
		}
	}
	
	public String signRequest(String request, String key) {
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),
					"HmacSHA1");
			mac.init(keySpec);
			mac.update(request.getBytes());
			byte[] encryptedBytes = mac.doFinal();
			return urlencoding(Base64.encodeBytes(encryptedBytes), "UTF-8");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return null;
	}

	public String urlencoding(String str, String charset) {
		String retStr = str;
		try {
			retStr = URLEncoder.encode(str, charset).replace("+", "%20")
					.replace("*", "%2A").replace("%7E", "~");
			;
		} catch (Exception e) {
		}
		return retStr;
	}
	
	public String cmdEncoding(String command){
		String result = null;
		try {
			String host = "https://api.ucloudbiz.olleh.com/server/v1/client/api";
			String apiUrl = "command=" + command;
			// String apiUrl = "command=listAvailableProductTypes";
			
			// kt 유클라우드 계정 생성 시 할당 받은 apikey 와 secretkey 입력
			String apiKey = "KC0kZSMj2lkpaMUk9MDagGXlUxM8XXqKn0rLYT5xHhs4ISNJEZNT9Ch13fmiRp5mxy0ndwuzp4PzZrqWh4M29g";
			String secretKey = "1TRUSLgHFubr8w2Gjh3dp9JgM_wyTn26nqEECxSd8tJOipQFlYm9pa5rMK_V1rEsJB_2cm4cbGvZyPG6Idr1zw";

			// Step 1: Make sure your APIKey is URL encoded
			String encodedApiKey = urlencoding(apiKey, "UTF-8");

			// Step 2: URL encode each parameter value, then sort the parameters
			// and
			// apiKey in
			// alphabetical order, and then toLowerCase all the parameters,
			// parameter values and apiKey.
			// Please note that if any parameters with a '&' as a value will
			// cause
			// this test client to fail since we are using
			// '&' to delimit
			// the string
			List<String> sortedParams = new ArrayList<String>();
			sortedParams.add("apikey=" + encodedApiKey.toLowerCase());
			StringTokenizer st = new StringTokenizer(apiUrl, "&");
			String url = null;
			boolean first = true;
			while (st.hasMoreTokens()) {
				String paramValue = st.nextToken();
				String param = paramValue.substring(0, paramValue.indexOf("="));
				String value = "";
				if ("userdata".equals(param)) {
					value = (paramValue.substring(paramValue.indexOf("=") + 1,
							paramValue.length()));
				} else {
					value = urlencoding(paramValue.substring(
							paramValue.indexOf("=") + 1, paramValue.length()),
							"UTF-8");
				}

				if (first) {
					url = param + "=" + value;
					first = false;
				} else {
					url = url + "&" + param + "=" + value;
				}
				sortedParams.add(param.toLowerCase() + "="
						+ value.toLowerCase());
			}
			Collections.sort(sortedParams);

			// System.out.println("Sorted Parameters: " + sortedParams);

			// Step 3: Construct the sorted URL and sign and URL encode the
			// sorted
			// URL with your secret key
			String sortedUrl = null;
			first = true;
			for (String param : sortedParams) {
				if (first) {
					sortedUrl = param;
					first = false;
				} else {
					sortedUrl = sortedUrl + "&" + param;
				}
			}
			// System.out.println("sorted URL : " + sortedUrl);
			String encodedSignature = signRequest(sortedUrl, secretKey);

			// Step 4: Construct the final URL we want to send to the CloudStack
			// Management Server
			// Final result should look like:
			// http(s)://://client/api?&apiKey=&signature=
			String finalUrl = host + "?" + url + "&apiKey=" + apiKey
					+ "&signature=" + encodedSignature;
			// System.out.println(finalUrl);
			// System.out.println("final URL : " + finalUrl);

			// System.out.println(encodedSignature);

			if (finalUrl.indexOf("https") != -1) {
				ProtocolSocketFactory socketFactory = new EasySSLProtocolSocketFactory();
				Protocol https = new Protocol("https", socketFactory, 443);
				Protocol.registerProtocol("https", https);
			}
			HttpClient client = new HttpClient();
			HttpMethod method = new GetMethod(finalUrl);
			int responseCode = client.executeMethod(method);
			if (responseCode == 200) {	
				// SUCCESS!
				System.out.println("Successfully executed command");
//				System.out.println("Success Result : "
//						+ method.getResponseBodyAsString());
				result = method.getResponseBodyAsString();
			} else {
				// FAILED!
				System.out.println("Unable to execute command with response code: " + responseCode);
				System.out.println("Fail Result : " + method.getResponseBodyAsString());
				result = method.getResponseBodyAsString();
			}
		} catch (Throwable t) {
			System.out.println(t);
		}
		return result;
	}
	
	public void cmdExec(String command){
		int time = 0;
		String reponse = this.cmdEncoding(command);
		
		String[] temp = reponse.split("jobid");
		String jobid = temp[1].substring(1, temp[1].length()-2);
	
		// 비동기 명령어 실행 결과 확인 명령어
		command = "queryAsyncJobResult" 
				+ "&jobid=" + jobid;	
		
		String jobstatus = null;
		
		while(true){
			
			reponse = this.cmdEncoding(command);
			temp = reponse.split("jobstatus");
			jobstatus = temp[1].substring(1, temp[1].length()-2);
			
			if(jobstatus.equals("0")){
				System.out.println(command+"...processing("+time+")");
			}else if(jobstatus.equals("1")){
				System.out.println(command+"...success("+time+")");
				break;
			}else if(jobstatus.equals("2")){
				System.out.println(command+"..fail("+time+")");
				break;
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			time += 10;
		}
		
		// ip삭제 결과
		
		System.out.println("==========================================");		
		System.out.println(command+"...execute result");		
		System.out.println(reponse);
		System.out.println("==========================================");		
	}
}
