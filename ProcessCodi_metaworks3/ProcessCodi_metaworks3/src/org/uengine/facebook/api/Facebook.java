package org.uengine.facebook.api;

import java.io.BufferedReader;
import java.io.File;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


public class Facebook extends DefaultFacebook{
	
	/**
	 * facebook 객체 초기화 
	 * @param APIKey Facebook apllcation API key, User access_token
	 * @param request
	 * @return
	 */
	public Facebook(String APIKey, String access_token) throws Exception{
		super(APIKey, access_token);
	}
	
	/**
	 * 유저와 관련된 정보를 JSONObject 객체로 반환 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUserInfo(String user_id) throws Exception{
		URL url = new URL(GRAPH_URL+user_id+"?access_token="+fb_info.get("access_token")+"&callback=?");
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		StringBuffer me = new StringBuffer();
		while(true){
			String tmp = br.readLine();
			if(tmp == null) 
				break;
			me.append(tmp);
		}
		JSONObject my_info = (JSONObject)JSONSerializer.toJSON(me.substring(me.indexOf("?(")+2, me.indexOf(");")));
		return my_info;
	}
	
	public JSONObject getMyInfo() throws Exception{
		return getUserInfo("me/");
	}
	
	/**
	 * 사용자 세부 정보 가져오기 content_name -> books, likes, movies, interests...etc
	 * @param user_id
	 * @param content_name
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUserSpecificInfo(String user_id, String content_name) throws Exception{
		return defaultReceive(GRAPH_URL+user_id+"/"+content_name+"?access_token="+fb_info.get("access_token"));
	}
	
	/**
	 * News Feed불러오기 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getNewsFeed() throws Exception{
		return defaultReceive(GRAPH_URL+"me/home?access_token="+fb_info.get("access_token"));
	}
	
	/**
	 * Wall 에 정보 가져오기 
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	public JSONObject getProfileFeed(String user_id) throws Exception{
		return defaultReceive(GRAPH_URL+user_id+"/feed?access_token="+fb_info.get("access_token"));
	}
	
	public JSONObject getMyProfileFeed() throws Exception{
		return getProfileFeed("me");
	}

	/**
	 * 친구 리스트를 불러온다. 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getFriendLists() throws Exception{
		return defaultReceive(GRAPH_URL+"me/friends?access_token="+fb_info.get("access_token"));
	}

	/**
	 * Post된 글을 불러온다.  
	 * @param message_id 페이스북에 등록된 message ID
	 * @return 불러온 message와 관련된 정보 
	 * @throws Exception
	 */
	public JSONObject getPost(String message_id) throws Exception{
		String url = GRAPH_URL+message_id;
		return defaultReceive(url);
	}
	
	/**
	 * feed, status 로 글 보내기. 
	 * @param data keys : Facebook Feed API 에서 제공하는 파라미터 값들 
	 * @return 페이스북에 포스팅 된 message_id  
	 * @throws Exception
	 */
	public JSONObject sendPostToMyWall(Map<String, String> data) throws Exception{
		return defaultSend(GRAPH_URL+"me/feed", data);
	}

	public JSONObject sendLikes(String content_id) throws Exception{
		return defaultSend(content_id, null);
	}
	
	public JSONObject getLikes(String content_id) throws Exception{
		return defaultReceive(GRAPH_URL+content_id+"/likes?access_token="+fb_info.get("access_token"));
	}
		
	/**
	 * Commenct 달기 
	 * @param data keys : Facebook API에서 제공되는 파라미터 값들 
	 * @return
	 * @throws Exception
	 */
	public JSONObject sendComment(String post_id, Map<String, String> data) throws Exception{
		return defaultSend(GRAPH_URL+post_id+"/comments", data);
	}
	
	/**
	 * Comment 받아오기 
	 * @param comment_id
	 * @return
	 * @throws Excpetion
	 */
	public JSONObject getComment(String comment_id) throws Exception{
		return defaultReceive(GRAPH_URL+comment_id);
	}
	
	/**
	 * 이벤트 보내기. 
	 * @param data keys : Facebook event API 에서 제공하는 파라미터 값들 
	 * @return 페이스북에 포스팅 된 event_id 
	 * @throws Exception
	 */
	public JSONObject sendEvents(Map<String, String> data) throws Exception{
		return defaultSend(GRAPH_URL+"me/events", data);
	}
	
	/**
	 * Event 정보를 불러온다.   
	 * @param event_id 페이스북에 등록된 event ID
	 * @return 불러온 event와 관련된 정보 
	 * @throws Exception
	 */
	public JSONObject getEvent(String event_id) throws Exception{
		String url = GRAPH_URL+event_id;
		return defaultReceive(url);
	}
	
	/**
	 * 앨범을 만들거나, 수정한다. 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public JSONObject createAlbum(Map<String, String> data) throws Exception{
		return defaultSend(GRAPH_URL+"me/albums", data);		
	}
	
	/**
	 * Album 정보를 가져온다. 
	 * @param album_id
	 * @return
	 * @throws Exception
	 */
	public JSONObject getAlbum(String album_id) throws Exception{
		return defaultReceive(GRAPH_URL+album_id+"?access_token="+fb_info.get("access_token"));
	}
	
	/**
	 * @param data Facebook에서 제공되는 파라미터 값들, imagePath, message 필수 
	 * @return photo id 
	 * @throws Exception
	 */
	
	public JSONObject sendPhoto(Map<String, String> data) throws Exception{	
		String result = new String();
		PostMethod file_post = new PostMethod(GRAPH_URL+"me/photos");
		file_post.getParams().setBooleanParameter(HttpMethodParams.USE_EXPECT_CONTINUE, false);
		JSONObject result_json = new JSONObject();
		try {
			Part[] parts = new Part[data.size()+1];
			File file = new File(data.get("source"));
			data.remove("source");
			parts[0] = new FilePart("source", file.getName(), file);
			parts[1] = new StringPart("access_token",
					fb_info.get("access_token"));
			
			Iterator<String> data_key = data.keySet().iterator();
			int i=2;
			while(data_key.hasNext()){
				String key = data_key.next();
				parts[i] = new StringPart(key, data.get(key));
				i++;
			}

			file_post.setRequestEntity(new MultipartRequestEntity(parts, file_post.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
			int status = client.executeMethod(file_post);
			
			if (status == HttpStatus.SC_OK) {
				
			} else {
				//실패 원인 
				StringBuilder notificationsSendResponse = new StringBuilder();
				byte[] byteArrayNotifications = new byte[4096];
				for (int n; (n = file_post.getResponseBodyAsStream().read(
						byteArrayNotifications)) != -1;) {
					notificationsSendResponse.append(new String(
							byteArrayNotifications, 0, n));
				}
				result = notificationsSendResponse.toString();
				System.out.println("===File Upload Error : " + result);
			}
			result_json = readReturnMessage(file_post.getResponseBodyAsStream());
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getClass().getName() + " "
					+ ex.getMessage());
			ex.printStackTrace();
		} finally {
			file_post.releaseConnection();
			return result_json;
		}
	}
	
	/**
	 * Photo 정보를 불러온다.   
	 * @param photo_id 페이스북에 등록된 photo ID
	 * @return 불러온 photo와 관련된 정보 
	 * @throws Exception
	 */
	public JSONObject getPhoto(String photo_id) throws Exception{
		return defaultReceive(GRAPH_URL+photo_id+"?access_token="+fb_info.get("access_token"));
	}
	
	/**
	 * Note작성 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public JSONObject sendNote(Map<String, String> data) throws Exception{
		return defaultSend(GRAPH_URL+"me/notes", data);
	}
	
	/**
	 * Note불러오기 
	 * @param note_id
	 * @return
	 * @throws Exception
	 */
	public JSONObject getNote(String note_id) throws Exception{
		return defaultReceive(GRAPH_URL+note_id+"?access_token="+fb_info.get("access_token"));
	}
	
	/**
	 * GroupId 정보를 가져온다. 
	 * @param GroupId
	 * @return
	 * @throws Exception
	 * add YBS
	 */
	
	public JSONObject getMyGroupId() throws Exception{
		return defaultReceive(GRAPH_URL+"me/groups?access_token="+fb_info.get("access_token"));
	}
	
	/**
	 * Group에 자신의 글을 올린다. 
	 * @param APIKey_id
	 * @return
	 * @throws Exception
	 * add YBS
	 */
	public JSONObject sendPostToMyGroup(Map<String, String> data) throws Exception{
		return defaultSend(GRAPH_URL+fb_info.get("appkey")+"/feed", data);
	}
	
	/**
	 * 친구의 담벼락에 자신의 글을 올린다. 
	 * @param APIKey_id
	 * @return
	 * @throws Exception
	 * add YBS
	 */
	public JSONObject sendPostToMyFriend(Map<String, String> data) throws Exception{
		return defaultSend(GRAPH_URL+fb_info.get("appkey")+"/feed", data);
	}
	
}
