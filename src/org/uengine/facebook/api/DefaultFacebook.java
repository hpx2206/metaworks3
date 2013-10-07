package org.uengine.facebook.api;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class DefaultFacebook {
	public static final int SUCCESS = 0;
	public static final int ERROR = -1;
	
	public static final String GRAPH_URL = "https://graph.facebook.com/";
	
	protected Map<String, String> fb_info;
	//keys : access_token, uid, ( expires, session_key, sig, )
	protected static final String charset = "UTF-8";
	
	protected DefaultFacebook(String APIKey, String access_token) throws Exception{
		fb_info = new HashMap<String, String>();
		fb_info.put("access_token", access_token);
		fb_info.put("appkey", APIKey);
	}
	
	/**
	 * Post, Event 등 기본적인 데이터 보내는 기본 함수 
	 * @param url
	 * @param data
	 * @return
	 * @throws Exception
	 */
	protected JSONObject defaultSend(String url, Map<String, String> data) throws Exception{
		HttpConnectionManagerParams connectionParams = new HttpConnectionManagerParams();
		
		HttpConnectionManager manager = new SimpleHttpConnectionManager();
		manager.setParams(connectionParams);
		
		HttpClient client = new HttpClient(manager);
		PostMethod post = new PostMethod(url);
		
		NameValuePair params[] = new NameValuePair[data.size()+1];
		params[0] = new NameValuePair("access_token", fb_info.get("access_token"));

		if(data.size()>0){
			Iterator<String> data_key = data.keySet().iterator();
			int i=1;
			while(data_key.hasNext()){
				String key = data_key.next();
				params[i] = new NameValuePair(key, data.get(key));
				i++;
			}
		}
			
		post.setRequestBody(params);
		post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		int result = client.executeMethod(post);
		 
		JSONObject result_json = readReturnMessage(post.getResponseBodyAsStream());
		
		return result_json;
	}
	
	/**
	 * DATA정보를 받아오기 위한 기본 함수 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected JSONObject defaultReceive(String url) throws Exception{
		URL api_url = new URL(url);
		URLConnection conn = api_url.openConnection();
		conn.setDoOutput(true);
		
		JSONObject info = readReturnMessage(conn.getInputStream());
		
		return info;
	}
	
	public Map<String, String> getFb_cinfo() {
		return fb_info;
	}

	public void setFb_info(Map<String, String> fb_info) {
		this.fb_info = fb_info;
	}
	
	/**
	 * URLConncetion으로 보낼 때 파라미터 설정 
	 * @param data 페이스북 API에서 지원하는 데이터들  
	 * @return  URLConnection으로 보낼 때 파라이터 형식 
	 * @throws Exception
	 */
	protected StringBuffer setParameters(Map<String, String> data) throws Exception{
		Iterator<String> data_key = data.keySet().iterator();
		StringBuffer parameters = new StringBuffer();
		parameters.append(URLEncoder.encode("access_token", charset));
		parameters.append("=");
		parameters.append(URLEncoder.encode(fb_info.get("access_token"), charset));
		parameters.append("&");
		while(data_key.hasNext()){
			String key = data_key.next();
			parameters.append(URLEncoder.encode(key, charset));
			parameters.append("=");
			parameters.append(URLEncoder.encode(data.get(key), charset));
			parameters.append("&");
		}
		parameters.deleteCharAt(parameters.length()-1);
		
		return parameters;
	}

	/**
	 * Facebook 
	 * @param date dd/MM/YY 형식의 date 
	 * @param hour HH:mmam or HH:mmpm 형식의 hour  
	 * @return 페이스북에서 지원하는 날짜 형식 
	 */
	public String replaceFacebookTimeForm(String date, String hour){
		StringBuffer fb_date_form = new StringBuffer();
		StringBuffer start_hour = new StringBuffer(hour);
		String[] start_date = date.split("/");
		
		if(start_hour.substring(start_hour.length()-2).equals("pm")){
			int tmp = Integer.parseInt(start_hour.substring(0, start_hour.indexOf(":")))+12;
			start_hour.replace(0, start_hour.indexOf(":"), String.valueOf(tmp));
		}
		start_hour.delete(start_hour.length()-2, start_hour.length());
		fb_date_form.append(start_date[2]).append("-").append(start_date[1]).append("-").append(start_date[0]).append("T").append(start_hour).append(":00");
		
		return fb_date_form.toString();
	}
	
	
	/**
	 * InputStream로 넘어온 결과값을 JSONObject로 변환시켜준다. 
	 * @param br
	 * @return jsonobject형식 
	 * @throws Exception
	 */
	protected JSONObject readReturnMessage(InputStream in) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		StringBuffer result = new StringBuffer();
		while(true){
			String tmp = br.readLine();
			if(tmp == null) 
				break;
			result.append(tmp);
		}
		
		JSONObject result_json = (JSONObject)JSONSerializer.toJSON(result.toString());
		
		return result_json;
		
	}
	
}
