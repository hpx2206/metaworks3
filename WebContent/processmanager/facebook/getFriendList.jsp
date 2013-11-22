<%@page import="net.sf.json.JSONArray"%><%@page import="net.sf.json.JSONObject"%><%@page import="org.uengine.facebook.api.Facebook"%>
<%@page import="org.uengine.kernel.GlobalContext"%>
<%@page import="java.util.HashMap"%><%@page import="java.util.Map"%>
<%@page import="org.uengine.facebook.activities.WriteFacebookActivity"%>
<%@ page contentType="text/xml; charset=UTF-8" language="java" import="org.uengine.util.XMLUtil,java.sql.*,javax.sql.*,org.uengine.util.dao.DefaultConnectionFactory"%><%
	response.setContentType("text/xml; charset=UTF-8");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
	
	String myappkey = request.getParameter("myappkey");
	String access_token = request.getParameter("accessToken");

	Map<String, String> data = new HashMap<String, String>();

	Facebook graph_getFriendLists = new Facebook(myappkey, access_token);  
	JSONObject friend = graph_getFriendLists.getFriendLists();
	JSONArray arrayGroup = friend.getJSONArray("data");

	for(int i = 0 ; i < arrayGroup.size() ; i++){
        JSONObject groupJSON = arrayGroup.getJSONObject(i);
        data.put(groupJSON.getString("id"), groupJSON.getString("name"));
	}
	
	String xml = GlobalContext.serialize(data, null);
	out.print(xml);
%>