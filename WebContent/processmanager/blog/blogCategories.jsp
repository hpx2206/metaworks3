<%@page import="org.uengine.kernel.GlobalContext"%>
<%@page import="org.apache.xmlrpc.client.XmlRpcClientConfigImpl"%><%@page import="org.apache.xmlrpc.client.XmlRpcClient"%><%@page import="java.net.URL"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
	String apiUrl = request.getParameter("apiUrl");
	String blogId = request.getParameter("blogId");
	String userName = request.getParameter("userName");
	String password = request.getParameter("password");
	
	XmlRpcClient client = new XmlRpcClient();
	XmlRpcClientConfigImpl configImpl = new XmlRpcClientConfigImpl();
	configImpl.setServerURL(new URL(apiUrl));
	client.setConfig(configImpl);

	Object[] cats = new Object[]{};
	try {
		cats = (Object[]) client.execute("metaWeblog.getCategories", new Object[] { blogId, userName, password });
	} catch (Exception e) {
		e.printStackTrace();
	}
	out.print(GlobalContext.serialize(cats , null));
%>