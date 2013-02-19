package org.uengine.codi.mw3.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.metaworks.Refresh;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.IFrame;
import org.metaworks.widget.ModalWindow;
import org.uengine.kernel.GlobalContext;

public class RemoteConferenceWorkItem extends WorkItem{
	
	public RemoteConferenceWorkItem() {
		setType(WORKITEM_TYPE_REMOTECONF);
	}


	@Override
	public Object[] add() throws Exception {
	
		Object[] returnData =  super.add();
		
		boolean record = true;
		
		String salt = GlobalContext.getPropertyString("bbb.security.salt");
		
		String createParam = "name=" + URLEncoder.encode(session.getUser().getName(), "UTF-8") + "&record=" + record + "&meetingID=" + getTaskId();// + "&attendeePW=" + password;
		String checkSum = hex_sha1("create" + createParam + salt);
		String callURI = "/bigbluebutton/api/create?" + createParam + "&checksum=" + checkSum;
		
		HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		httpClient.getHostConfiguration().setHost(GlobalContext.getPropertyString("bbb.server.host"), Integer.parseInt(GlobalContext.getPropertyString("bbb.server.port")), "http");
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
 		String salt = GlobalContext.getPropertyString("bbb.security.salt");
		
		String meetingID = databaseMe().getExt1();
		String attendeePW = databaseMe().getExt2();
		
		String joinParam = "meetingID=" + meetingID + "&fullName=" + URLEncoder.encode(session.getUser().getName(), "UTF-8") + "&password=" + attendeePW;
		String joinCheckSum = hex_sha1("join" + joinParam + salt);
		String joinURI = "join?" + joinParam + "&checksum=" + joinCheckSum;
				
		return new ModalWindow(new IFrame("http://"+ GlobalContext.getPropertyString("bbb.server.host") + "/bigbluebutton/api/" + joinURI), 1000, 800, databaseMe().getTitle());
	}
	
	@ServiceMethod
	public void end() throws Exception {
		
		String salt = GlobalContext.getPropertyString("bbb.security.salt");
		
		String meetingID = databaseMe().getExt1();
		String moderatorPW = databaseMe().getExt3();
		
		String endParam = "meetingID=" + meetingID + "&password=" + moderatorPW ;
		String checkSum = hex_sha1("end" + endParam + salt);
		String endURI = "end?" + endParam + "&checksum=" + checkSum;
		
//		String getRecordURI = getRecordingInfo();
		
		URL url = new URL("http://" + GlobalContext.getPropertyString("bbb.server.host") + "/bigbluebutton/api/" + endURI);
		
        URLConnection urlConn = url.openConnection();
        BufferedReader burr = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

        burr.close();
        
		databaseMe().setStatus("Completed");
		setWriter(session.getUser());
		setTitle(databaseMe().getTitle());
//		databaseMe().setExt4(getRecordURI);
		
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
//		httpClient.getHostConfiguration().setHost(GlobalContext.getPropertyString("bbb.server.host"), Integer.parseInt(GlobalContext.getPropertyString("bbb.server.port")), "http");
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
//			url = "http://" + GlobalContext.getPropertyString("bbb.server.host")+ "/playback/slides/playback.html?meetingId=" + recordID;
//			
//		}
//		
//		databaseMe().setExt4(url);
//		
//		return url;
//		
//	}
	
	@ServiceMethod
	public IFrame palyBack() throws Exception{
		String salt = GlobalContext.getPropertyString("bbb.security.salt");
		
		String meetingID = databaseMe().getExt1();
		
		String recordParam = "meetingID=" + meetingID;
		String checkSum = hex_sha1("getRecordings" + recordParam + salt);
		String recordURI = "/bigbluebutton/api/getRecordings?" + recordParam + "&checksum=" + checkSum;
		
		String url = null;
		String recordID = null;
		
		HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		httpClient.getHostConfiguration().setHost(GlobalContext.getPropertyString("bbb.server.host"), Integer.parseInt(GlobalContext.getPropertyString("bbb.server.port")), "http");
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
		
		if(rootElement.getChild("messageKey").equals("noRecordings")){
			System.out.println("오류뱉어 !!");
		}else {
			Element childs = rootElement.getChild("recordings").getChild("recording");
			List<Element> childsList = childs.getChildren();
			
			for(Element elem : childsList){
				if("recordID".equals(elem.getName())){
					recordID = elem.getValue();
				}
			}
		}
		
		is.close();
		
		url = "http://" + GlobalContext.getPropertyString("bbb.server.host")+ "/playback/slides/playback.html?meetingId=" + recordID;
		
		return new IFrame(url);
		
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
	
}
