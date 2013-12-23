package org.uengine.codi.mw3.project.oce;

import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.uengine.codi.util.Base64;
import org.uengine.kernel.GlobalContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class KTConnectionFectory implements IaaSConnectionFectory{

	String templateid;
		public String getTemplateid() {
			return templateid;
		}
		public void setTemplateid(String templateid) {
			this.templateid = templateid;
		}
	
	String vmName;
		public String getVmName() {
			return vmName;
		}
		public void setVmName(String vmName) {
			this.vmName = vmName;
		}
		
	String jobId;
		public String getJobId() {
			return jobId;
		}
		public void setJobId(String jobId) {
			this.jobId = jobId;
		}
		
	ServerInfo serverInfo; 
		public ServerInfo getServerInfo() {
			return serverInfo;
		}
		public void setServerInfo(ServerInfo serverInfo) {
			this.serverInfo = serverInfo;
		}
		
	@Override
	public void createServer() throws Exception {
		/*
		String findAvailableList = "listAvailableProductTypes";
		String reponses = this.cmdRequest(findAvailableList);
		
		InputSource is = new InputSource(new StringReader(reponses));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		
        XPath xpath = XPathFactory.newInstance().newXPath();
        
        NodeList cols = (NodeList)xpath.evaluate("//producttypes", document, XPathConstants.NODESET);
        for( int idx=0; idx<cols.getLength(); idx++ ){
            System.out.println(cols.item(idx).getTextContent());
        	NodeList childs = cols.item(idx).getChildNodes();
        	if( idx == 3 ){
	        	for( int j=0; j<childs.getLength(); j++ ){
	        		System.out.println("nodeName : " + childs.item(j).getNodeName() );
	        		System.out.println("Text : " + childs.item(j).getTextContent() );
	        	}
        	}
        }
		*/
		/*
			serviceofferingid	서비스 제공 ID [ CPU, Memory 조합 : 2 core 1GB Mem ]
			templateid			템플릿 ID [ OS : WIN2003 KOR ENT SP2 32bit ]
			diskofferingid		디스크 제공 ID
			zoneid				zone ID (** 상품 리스트에 나오는 zoneid만 가능)
		*/
		
		String command = "deployVirtualMachine"
				+ "&serviceofferingid=0ba1379e-114c-4502-b9c5-c4c85492504d"	// premium 1vCore 2GB RAM
				+ "&templateid=d02bd7e0-a831-45fa-a5da-a187a4fe3e2c"			// Centos 5.4 32bit
				+ "&diskofferingid=87c0a6f6-c684-4fbe-a393-d8412bcf788d"		// 100GB
				+ "&zoneid=9845bd17-d438-4bde-816d-1b12f37d5080"				// KOR-Central B
				+ "&displayname=" + getVmName();
				
		String reponse = this.cmdRequest(command);
		
		String[] temp = reponse.split("jobid");
		String jobid = temp[1].substring(1, temp[1].length()-2);
		
		this.getServerInfo().databaseMe().setJobId(jobid);
		this.getServerInfo().databaseMe().setStatus(SERVER_STATUS_DEPLOYING);
		
		
	}
	
	@Override
	public void startServer() throws Exception {
		
	}
	
	@Override
	public void stopServer() throws Exception {
		
	}

	@Override
	public void deleteServer() throws Exception {
		String command = "stopVirtualMachine&id=" + serverInfo.getVmId();
		
		String reponse = this.cmdRequest(command);
		
		String[] temp = reponse.split("jobid");
		String jobid = temp[1].substring(1, temp[1].length()-2);
		this.setJobId(jobid);
		
		this.getServerInfo().databaseMe().setJobId(jobid);
		this.getServerInfo().databaseMe().setStatus(SERVER_STATUS_DEPLOYING);
		
		// vm delete
		command = "destroyVirtualMachine&id="+serverInfo.getVmId();	
	}

	@Override
	public void serverInfo() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String serverStatus() throws Exception {
		// this.getJobId() 에 따라, 상태값이 나온다.
		
		String command = "queryAsyncJobResult" 
				+ "&jobid=" + this.getJobId();	
		
		String reponse = this.cmdRequest(command);
		
		String[] cmds = reponse.split("cmd");						// 수행 명령어
		String cmd = cmds[1].substring(1, cmds[1].length()-2);
		String[] jobstatuss = reponse.split("jobstatus");			// 현재 작업 상태. (0:진행 중, 1:성공, 2:실패 [jobresult 에 결과 표시]) 
		String jobstatus = jobstatuss[1].substring(1, jobstatuss[1].length()-2);
		
		System.out.println(" cmd = " + cmd);
		System.out.println(" jobstatus = " + jobstatus);
		
		if( "com.cloud.api.commands.DeployVMCmd".equals(cmd)){		// 서버 생성
			if(jobstatus.equals("0")){
				return IaaSConnectionFectory.SERVER_STATUS_CREATING;
			}else if(jobstatus.equals("1")){
				
				InputSource is = new InputSource(new StringReader(reponse));
		        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
				
		        XPath xpath = XPathFactory.newInstance().newXPath();
		        
		        Node node = (Node)xpath.evaluate("/queryasyncjobresultresponse/jobresult/virtualmachine/nic/ipaddress", document, XPathConstants.NODE);
		        System.out.println("    ======== " + node.getTextContent());
		        
		        Node node1 = (Node)xpath.evaluate("/queryasyncjobresultresponse/jobresult/virtualmachine/id", document, XPathConstants.NODE);
		        System.out.println("    ======== " + node1.getTextContent());
		        
		        Node node2 = (Node)xpath.evaluate("/queryasyncjobresultresponse/jobresult/virtualmachine/password", document, XPathConstants.NODE);
		        System.out.println("    ======== " + node2.getTextContent());
		        
		        this.getServerInfo().databaseMe().setJobId(null);
				this.getServerInfo().databaseMe().setStatus(SERVER_STATUS_RUNNING);
				this.getServerInfo().databaseMe().setVmIp(node.getTextContent());
				this.getServerInfo().databaseMe().setVmId(node1.getTextContent());
				this.getServerInfo().databaseMe().setVmPassword(node2.getTextContent());
				
				return IaaSConnectionFectory.SERVER_STATUS_RUNNING;
			}else if(jobstatus.equals("2")){
				
				this.getServerInfo().databaseMe().setJobId(null);
				this.getServerInfo().databaseMe().setStatus(SERVER_STATUS_DEPLOYFAIL);
				
				return IaaSConnectionFectory.SERVER_STATUS_DEPLOYFAIL;
			}
		}else if ("com.cloud.api.commands.StartVMCmd".equals(cmd)){			// 서버 시작
			return null;
		}else if ("com.cloud.api.commands.AssociateIPAddrCmd".equals(cmd)){	// 공인 IP 생성
			if(jobstatus.equals("0")){
				return IaaSConnectionFectory.SERVER_STATUS_REQUEST;
			}else if(jobstatus.equals("1")){
				String[] temp = reponse.split("<ipaddress>");
				temp = temp[2].split("</ipaddress>");
				String ip = temp[0];
				
				temp = reponse.split("<id>");
				String ip_id = temp[1].substring(0, temp[1].lastIndexOf("</id>"));
				
				this.getServerInfo().databaseMe().setJobId(null);
				this.getServerInfo().databaseMe().setTempId(ip_id);
				this.getServerInfo().databaseMe().setVmOutsideIp(ip);
				this.getServerInfo().databaseMe().setStatus(SERVER_STATUS_RUNNING);
			}else if(jobstatus.equals("2")){
				this.getServerInfo().databaseMe().setJobId(null);
				this.getServerInfo().databaseMe().setStatus(SERVER_STATUS_IPREQUSETFAIL);
				
				return IaaSConnectionFectory.SERVER_STATUS_DEPLOYFAIL;
			}
			return null;
		}
		return null;
	}

	@Override
	public void createOutsideIP() throws Exception {
		// TODO Auto-generated method stub
		String command = "associateIpAddress" +
				"&zoneid=9845bd17-d438-4bde-816d-1b12f37d5080";
		
		String reponse = this.cmdRequest(command);
		
		String[] temp = reponse.split("jobid");
		String jobid = temp[1].substring(1, temp[1].length()-2);
		
		this.getServerInfo().databaseMe().setJobId(jobid);
		this.getServerInfo().databaseMe().setStatus(SERVER_STATUS_REQUEST);	 // request 
	}

	@Override
	public int getCreatedOutsideIPStatus() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteOutsideIP() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDeletedOutsideIPStatus() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPortForwardingList() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPortForwarding() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPortForwardingStatus() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String cmdRequest(String command){
		String result = null;
		try {
			String host = GlobalContext.getPropertyString("iaas.host", null);
			String apiUrl = "command=" + command;
			// String apiUrl = "command=listAvailableProductTypes";
			
			// kt 유클라우드 계정 생성 시 할당 받은 apikey 와 secretkey 입력
			String apiKey = GlobalContext.getPropertyString("iaas.apiKey", null);
			String secretKey = GlobalContext.getPropertyString("iaas.secretKey", null);

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
}
