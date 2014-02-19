package org.uengine.codi.mw3.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.IFrame;
import org.metaworks.widget.ModalWindow;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.kernel.GlobalContext;

public class RemoteConferenceWorkItem extends WorkItem{
	
	final public String BBBSalt = GlobalContext.getPropertyString("bbb.security.salt");
	final public String BBBHost = GlobalContext.getPropertyString("bbb.server.host","localhost");
	final public String BBBPort = GlobalContext.getPropertyString("bbb.server.port","80");
	
	public RemoteConferenceWorkItem() {
		setType(WORKITEM_TYPE_REMOTECONF);
	}


	@Override
	public Object[] add() throws Exception {
	
		Object[] returnData =  super.add();
		
		boolean record = true;
		
		String createParam = "name=" + URLEncoder.encode(session.getUser().getName(), "UTF-8") + "&record=" + record + "&meetingID=" + getTaskId();// + "&attendeePW=" + password;
		String checkSum = hex_sha1("create" + createParam + BBBSalt);
		String callURI = "/bigbluebutton/api/create?" + createParam + "&checksum=" + checkSum;
		
		HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		httpClient.getHostConfiguration().setHost(GlobalContext.getPropertyString("bbb.server.host", "localhost"), Integer.parseInt(BBBPort), "http");
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			
		GetMethod getMethod = new GetMethod(callURI);

		httpClient.executeMethod(getMethod);
		
		InputStream is = getMethod.getResponseBodyAsStream();
		
		SAXBuilder builder = new SAXBuilder();
		builder.setValidation(false);
		builder.setExpandEntities(false);
		builder.setIgnoringElementContentWhitespace(true);

		Document documentToAttach = builder.build(is);
		Element rootElement = documentToAttach.getRootElement();
		List<Element> childs = rootElement.getChildren();
		
		String meetingID = null;
		String attendeePW = null;
		String moderatorPW = null;
		
		for(Element elem : childs){
			if("meetingID".equals(elem.getName())){
				meetingID = elem.getValue();
			}else if("attendeePW".equals(elem.getName())){
				attendeePW = elem.getValue();
			}else if("moderatorPW".equals(elem.getName())){
				moderatorPW = elem.getValue();
			}
		}
		
		is.close();
		
		databaseMe().setExt1(meetingID);
		databaseMe().setExt2(attendeePW);
		databaseMe().setExt3(moderatorPW);
		
		return returnData;
	}

	
	@ServiceMethod(target="popup")
	public ModalWindow join() throws Exception{
 		
		String meetingID = databaseMe().getExt1();
		String attendeePW = databaseMe().getExt2();
		String moderatorPW = databaseMe().getExt3();
		String title = databaseMe().getTitle();
		String endPoint = databaseMe().getEndpoint();
		String joinParam=null;
		
		System.out.println(session.getEmployee().getEmail());
		
		joinParam = "meetingID=" + meetingID + "&fullName=" + URLEncoder.encode(session.getUser().getName(), "UTF-8");
				
		if(session.getEmployee().getEmpCode().equals(endPoint))
			joinParam += "&password=" + moderatorPW;
		else
			joinParam += "&password=" + attendeePW;
		
		
		String joinCheckSum = hex_sha1("join" + joinParam + BBBSalt);
		String joinURI = "join?" + joinParam + "&checksum=" + joinCheckSum;
		
		IFrame iframe = new IFrame();
		iframe.setSrc("http://"+ BBBHost + "/bigbluebutton/api/" + joinURI);
		iframe.setWidth("100%");
				
		return new ModalWindow(iframe, 1000, 550, title);
	}
	
	@ServiceMethod
	public void end() throws Exception {
		
		if(!"0".equals(isMeetingAttendee())){
			throw new Exception("회의가 진행 중입니다.");
		}
		
		String salt = GlobalContext.getPropertyString("bbb.security.salt");
		
		String meetingID = databaseMe().getExt1();
		String moderatorPW = databaseMe().getExt3();
		
		String endParam = "meetingID=" + meetingID + "&password=" + moderatorPW ;
		String checkSum = hex_sha1("end" + endParam + salt);
		String endURI = "end?" + endParam + "&checksum=" + checkSum;
		
//		String getRecordURI = getRecordingInfo();
		
		URL url = new URL("http://" + BBBHost + "/bigbluebutton/api/" + endURI);
		
        URLConnection urlConn = url.openConnection();
        BufferedReader burr = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

        burr.close();
        
		databaseMe().setStatus("Waited");
		setWriter(session.getUser());
		setTitle(databaseMe().getTitle());
		
		recodingCheck();
		
//		HashMap<String, String> pushUserMap = new HashMap<String, String>();
//		Notification notification = new Notification();
//		notification.session = session;
//		pushUserMap = notification.findTopicNotiUser(String.valueOf(this.getTaskId()));
//		String currentUserId = session.getUser().getUserId();
//		
//		MetaworksRemoteService.pushClientObjectsFiltered(
//				new OtherSessionFilter(pushUserMap, currentUserId.toUpperCase()),
//				new Object[]{new WorkItemListener(WorkItemListener.COMMAND_REFRESH , this)});
	
		
	}

	public String isMeetingRunning() throws Exception{
		
		String meetingID = databaseMe().getExt1();
		
		String param = "meetingID=" + meetingID;
		String checksum = hex_sha1("isMeetingRunning" + param + BBBSalt);
		String url = "http://" + BBBHost + "/bigbluebutton/api/isMeetingRunning?"+ param + "&checksum=" + checksum;
		
		
		HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		httpClient.getHostConfiguration().setHost(BBBHost, Integer.parseInt(BBBPort), "http");
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			
		GetMethod getMethod = new GetMethod(url);

		httpClient.executeMethod(getMethod);
		
		InputStream is = getMethod.getResponseBodyAsStream();
		
		SAXBuilder builder = new SAXBuilder();
		builder.setValidation(false);
		builder.setExpandEntities(false);
		builder.setIgnoringElementContentWhitespace(true);

		Document documentToAttach = builder.build(is);
		Element rootElement = documentToAttach.getRootElement();
		List<Element> childs = rootElement.getChildren();
		
		String returnValue = "";
		
		for(Element elem : childs){
			if("running".equals(elem.getName())){
				returnValue = elem.getValue();
			}
		}
		
		return returnValue;
	}
	
	@ServiceMethod
	public String isMeetingAttendee() throws Exception{
		
		String meetingID = databaseMe().getExt1();
		String moderatorPW = databaseMe().getExt3();
		
		String param = "meetingID=" + meetingID + "&password=" + moderatorPW;
		String checksum = hex_sha1("getMeetingInfo" + param + BBBSalt);
		String url = "http://" + BBBHost + "/bigbluebutton/api/getMeetingInfo?"+ param + "&checksum=" + checksum;
		
		
		HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		httpClient.getHostConfiguration().setHost(BBBHost, Integer.parseInt(BBBPort), "http");
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			
		GetMethod getMethod = new GetMethod(url);

		httpClient.executeMethod(getMethod);
		
		InputStream is = getMethod.getResponseBodyAsStream();
		
		SAXBuilder builder = new SAXBuilder();
		builder.setValidation(false);
		builder.setExpandEntities(false);
		builder.setIgnoringElementContentWhitespace(true);

		Document documentToAttach = builder.build(is);
		Element rootElement = documentToAttach.getRootElement();
		List<Element> childs = rootElement.getChildren();
		
		String returnValue = "";
		
		for(Element elem : childs){
			if("participantCount".equals(elem.getName())){
				returnValue = elem.getValue();
			}
		}
		
		return returnValue;
	}
	
	
	@ServiceMethod(callByContent=true)
	public Object recodingCheck() throws Exception{
		String salt = GlobalContext.getPropertyString("bbb.security.salt");
		
		String meetingID = databaseMe().getExt1();
		String recordParam = "meetingID=" + meetingID;
		String checkSum = hex_sha1("getRecordings" + recordParam + salt);
		String recordURI = "/bigbluebutton/api/getRecordings?" + recordParam + "&checksum=" + checkSum;
		
		String recordUrl = null;
		String recordID = null;
		
		HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		httpClient.getHostConfiguration().setHost(BBBHost, Integer.parseInt(BBBPort), "http");
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			
		GetMethod getMethod = new GetMethod(recordURI);

		httpClient.executeMethod(getMethod);
		
		InputStream is = getMethod.getResponseBodyAsStream();
		boolean returnFalg = false;
		try{
			SAXBuilder builder = new SAXBuilder();
			builder.setValidation(false);
			builder.setExpandEntities(false);
			builder.setIgnoringElementContentWhitespace(true);
	
			Document documentToAttach = builder.build(is);
			Element rootElement = documentToAttach.getRootElement();
			
			Element childs = rootElement.getChild("recordings").getChild("recording");
			if( childs != null ){
				List<Element> childsList = childs.getChildren();
				
				for(Element elem : childsList){
					if("recordID".equals(elem.getName())){
						recordID = elem.getValue();
					}
				}
				recordUrl = "http://" + BBBHost+ "/playback/presentation/playback.html?meetingId=" + recordID;
				IFrame iframe = new IFrame();
				iframe.setSrc(recordUrl);
				iframe.setWidth("100%");
				this.setConference(iframe);
				this.setContentLoaded(true);
				returnFalg = true;
				databaseMe().setStatus("Completed");
				databaseMe().setExt4(recordUrl);

			}else{
				
				IFrame iframe = new IFrame();
				String loadingUrl = TenantContext.getURL(null) 
						+ "dwr/metaworks/images/circleloading.gif";
				
				iframe.setSrc(loadingUrl);
				iframe.setWidth("100%");
				this.setConference(iframe);
				this.setContentLoaded(false);
				returnFalg = false;

				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			is.close();
			
		}
		if( returnFalg ){
			return this;
		}else{
			return null;
		}
		
	}

//	private String getRecordingInfo() throws Exception{
//		String salt = GlobalContext.getPropertyString("bbb.security.salt");
//		
//		String meetingID = databaseMe().getExt1();
//		
//		String recordParam = "meetingID=" + meetingID;
//		String checkSum = hex_sha1("getRecordings" + recordParam + salt);
//		String recordURI = "/bigbluebutton/api/getRecordings?" + recordParam + "&checksum=" + checkSum;
//		
//		HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
//		httpClient.getHostConfiguration().setHost(bbbHost, Integer.parseInt(bbbPort), "http");
//		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
//		
//		GetMethod getMethod = new GetMethod(recordURI);
//		
//		httpClient.executeMethod(getMethod);
//		
//		InputStream is = getMethod.getResponseBodyAsStream();
//		
//		SAXBuilder builder = new SAXBuilder();
//		builder.setValidation(false);
//		builder.setExpandEntities(false);
//		builder.setIgnoringElementContentWhitespace(true);
//		
//		Document documentToAttach = builder.build(is);
//		Element rootElement = documentToAttach.getRootElement();
//		Element childs = rootElement.getChild("recordings").getChild("recording");
//		
//		String url = null;
//		String recordID = null;
//		
//		if(childs != null){
//			
//			List<Element> childsList = childs.getChildren();
//			
//			
//			for(Element elem : childsList){
//				if("recordID".equals(elem.getName())){
//					recordID = elem.getValue();
//				}
//			}
//			
//			is.close();
//			
//			url = "http://" + bbbHost+ "/playback/slides/playback.html?meetingId=" + recordID;
//			
//		}
//		
//		databaseMe().setExt4(url);
//		
//		return url;
//		
//	}
	
	@ServiceMethod(target="popup")
	public ModalWindow palyBack() throws Exception{
		String salt = GlobalContext.getPropertyString("bbb.security.salt");
		
		String meetingID = databaseMe().getExt1();
		
		String recordParam = "meetingID=" + meetingID;
		String checkSum = hex_sha1("getRecordings" + recordParam + salt);
		String recordURI = "/bigbluebutton/api/getRecordings?" + recordParam + "&checksum=" + checkSum;
		
		String url = null;
		String recordID = null;
		
		HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		httpClient.getHostConfiguration().setHost(BBBHost, Integer.parseInt(BBBPort), "http");
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			
		GetMethod getMethod = new GetMethod(recordURI);

		httpClient.executeMethod(getMethod);
		
		InputStream is = getMethod.getResponseBodyAsStream();
		
		SAXBuilder builder = new SAXBuilder();
		builder.setValidation(false);
		builder.setExpandEntities(false);
		builder.setIgnoringElementContentWhitespace(true);

		Document documentToAttach = builder.build(is);
		Element rootElement = documentToAttach.getRootElement();
		
		try{
			Element childs = rootElement.getChild("recordings").getChild("recording");
			List<Element> childsList = childs.getChildren();
			
			for(Element elem : childsList){
				if("recordID".equals(elem.getName())){
					recordID = elem.getValue();
				}
			}
		}catch (Exception e) {
			throw new Exception("레코딩 변환작업 중입니다.");
		}
		
		is.close();
		
		url = "http://" + BBBHost+ "/playback/presentation/playback.html?meetingId=" + recordID;
		
		IFrame iframe = new IFrame();
		iframe.setSrc(url);
		iframe.setWidth("100%");
		iframe.setHeight("100%");
		
		return new ModalWindow(iframe, 1500, 750, databaseMe().getTitle());
		
	}
	
	@Override
	public void loadContents() throws Exception {
		this.setContentLoaded(true);
		
	}
	
	
	private String hex_sha1(String seed) throws NoSuchAlgorithmException{
		 MessageDigest mDigest = MessageDigest.getInstance("SHA1");
	        byte[] result = mDigest.digest(seed.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	        }
	         
	        return sb.toString();
	}
	
	ArrayList<String> initialFollowers;
		public ArrayList<String> getInitialFollowers() {
			return initialFollowers;
		}
		public void setInitialFollowers(ArrayList<String> initialFollowers) {
			this.initialFollowers = initialFollowers;
		}
	
}
