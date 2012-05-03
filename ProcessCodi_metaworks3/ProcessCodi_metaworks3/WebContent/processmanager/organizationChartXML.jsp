<?xml version="1.0" encoding="UTF-8"?>
<%
response.setContentType("text/xml; charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%><%@page 
	pageEncoding="KSC5601" 
	import="javax.naming.Context,javax.naming.InitialContext,javax.naming.NamingException,javax.rmi.PortableRemoteObject,org.uengine.kernel.*,java.util.*,java.io.*,org.uengine.processmanager.*"
%><%@ page import="com.liferay.portal.model.User" 
%><%@ page import="com.liferay.portal.ejb.*" 
%><%@ page import="com.liferay.portal.util.PortalUtil" 
%><%@ page import="com.liferay.portal.NoSuchUserException" 
%><resources><%
	User[] users = (User[])CompanyLocalManagerUtil.getUsers("liferay.com").toArray(new User[0]);
	
	for(int i=0; i<users.length; i++){
		%><resource value="<%= users[i].getEmailAddress() %>" name="<%= users[i].getFullName() %>"/><%
	}
%></resources>