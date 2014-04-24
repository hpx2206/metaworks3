package org.uengine.codi.mw3.project.oce;

import java.io.Serializable;
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
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.util.Base64;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerRemote;

public class KtProjectCreateRequest {
	
	CloudInfo cloudInfo;
		public CloudInfo getCloudInfo() {
			return cloudInfo;
		}
		public void setCloudInfo(CloudInfo cloudInfo) {
			this.cloudInfo = cloudInfo;
		}
	public ProcessManagerRemote processManager ;
		public ProcessManagerRemote getProcessManager() {
			return processManager;
		}
		public void setProcessManager(ProcessManagerRemote processManager) {
			this.processManager = processManager;
		}
		
	public CloudInfo createRequset(String vm_name , ProcessInstance instance) throws Exception {
		String vm_id;
		String vm_pass;
		String ip_id;
		String ip;
		int time = 0;
		
		
		//////////////
		//	VM 생성	//
		//////////////
		
		// templateid 값
		// Centos : b1c8383a-de6e-425f-ba18-12cadad827d3
		// Ubuntu : 6f7c2ce4-107e-44d7-abd3-4718c796e1f7
		// ubuntu_12.04_64bit_java_cubrid  : 396778cc-16a8-478d-b753-2ffd2a82fff9
		// ubuntu_12.04_64bit_java_cubrid_jboss  : b90c22c9-8ce8-4930-875a-153a13ea8d5d
		// ubuntu_12.04_64bit_java_mysql_jboss  :  bb4fe753-b23a-4296-8a1f-f85122f96a64
		
		// VM 생성 명령어
		String command = "deployVirtualMachine"
				+ "&serviceofferingid=b6216137-96ed-4d0a-bb95-fac81b3fe00b"
				+ "&templateid=bb4fe753-b23a-4296-8a1f-f85122f96a64"
				+ "&diskofferingid=87c0a6f6-c684-4fbe-a393-d8412bcf788d"
				+ "&zoneid=eceb5d65-6571-4696-875f-5a17949f3317"
				+ "&displayname=" + vm_name;
				
		String reponse = this.cmdRequest(command);
		
		String[] temp = reponse.split("jobid");
		String jobid = temp[1].substring(1, temp[1].length()-2);

		
		// 비동기 명령어 실행 결과 확인 명령어
		command = "queryAsyncJobResult" 
				+ "&jobid=" + jobid;	
		
		String jobstatus = null;
		String result = "";
		while(true){
			
			reponse = this.cmdRequest(command);
			temp = reponse.split("jobstatus");
			jobstatus = temp[1].substring(1, temp[1].length()-2);
			
			if(jobstatus.equals("0")){
				result = "서버생성중...";
			}else if(jobstatus.equals("1")){
				result = "서버생성성완료";
				break;
			}else if(jobstatus.equals("2")){
				result = "서버생성실패";
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
		// VM 생성 결과
		System.out.println(result);
//		System.out.println(reponse);
		
		
		temp = reponse.split("<id>");
		vm_id = temp[1].substring(0, temp[1].lastIndexOf("</id>"));
		
		temp = reponse.split("<password>");
		vm_pass = temp[1].substring(0, temp[1].lastIndexOf("</password>"));
		
		
		//////////////
		//	공인iP 생성	//
		//////////////

		command = "associateIpAddress" +
				"&zoneid=eceb5d65-6571-4696-875f-5a17949f3317";
		
		reponse = this.cmdRequest(command);
		
		temp = reponse.split("jobid");
		jobid = temp[1].substring(1, temp[1].length()-2);
		
		command = "queryAsyncJobResult" +
    			"&jobid=" + jobid;
		
		jobstatus = null;
		String result1 = "";
		while(true){
			
			reponse = this.cmdRequest(command);
			temp = reponse.split("jobstatus");
			jobstatus = temp[1].substring(1, temp[1].length()-2);
			
			if(jobstatus.equals("0")){
				result1 = "아이피생성중...";
			}else if(jobstatus.equals("1")){
				result1 = "아이피생성완료";
				break;
			}else if(jobstatus.equals("2")){
				result1 = "아이피생성실패";
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
		// 공인ip 생성 결과
		System.out.println(result1);
//		System.out.println(reponse);
		
		temp = reponse.split("<ipaddress>");
		temp = temp[2].split("</ipaddress>");
		ip = temp[0];
		
		temp = reponse.split("<id>");
		ip_id = temp[1].substring(0, temp[1].lastIndexOf("</id>"));
		
		
		//////////////////
		//	VM에 ip 할당	//
		//////////////////
	
		command = "createPortForwardingRule" +
				"&ipaddressid="+ip_id+
				"&virtualmachineid="+vm_id+
				"&protocol=TCP" +
				"&privateport=1" +
				"&privateendport=8080" +
				"&publicport=1" +
				"&publicendport=8080";
		
		reponse = this.cmdRequest(command);
		
		temp = reponse.split("jobid");
		jobid = temp[1].substring(1, temp[1].length()-2);
		
		command = "queryAsyncJobResult" +
    			"&jobid=" + jobid;
		
		
		jobstatus = null;
		String result2 = "";
		while(true){
			
			reponse = this.cmdRequest(command);
			temp = reponse.split("jobstatus");
			jobstatus = temp[1].substring(1, temp[1].length()-2);
			
			if(jobstatus.equals("0")){
				result2 = "아이피할당중...";
			}else if(jobstatus.equals("1")){
				result2 = "아이피할당완료";
				break;
			}else if(jobstatus.equals("2")){
				result2 = "아이피할당실패";
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
		reponse = this.cmdRequest(command);
		
		// ip 할당 결과
		System.out.println(result2);
//		System.out.println(reponse);
			
		System.out.println("====================================================");
		System.out.println("생성결과");
		System.out.println("접속url : "+ip);
		System.out.println("id : root 또는  Administrator");
		System.out.println("pw : "+vm_pass);
		System.out.println("====================================================");
		
		String resultMessage = result + "<br/>" + result1 + "<br/>" + result2;
		if( processManager != null && instance != null){
			instance.setBeanProperty("ManagerApproval.resultIp", (Serializable)ip);
			instance.setBeanProperty("ManagerApproval.resultStr", (Serializable)resultMessage);		
			processManager.applyChanges();
		}
		
		CloudInfo cloudInfo = new CloudInfo();
		cloudInfo.setServerName(vm_name);
		cloudInfo.setServerIp(ip);
		cloudInfo.setServerIpId(ip_id);
		cloudInfo.setServerId(vm_id);
		cloudInfo.setRootId("root");
		cloudInfo.setRootPwd(vm_pass);
		
		return cloudInfo;
		
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
	
	public String cmdRequest(String command){
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
//				System.out.println("Successfully executed command");
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

}
