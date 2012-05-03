<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" errorPage="/bpm/common/callback/errorpage.jsp"%>

<%@ page import="java.util.*,javax.naming.InitialContext,javax.transaction.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="javax.rmi.PortableRemoteObject"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.NamingException"%>
<%@ page import="org.uengine.processmanager.*"%>
<%@ page import="org.uengine.kernel.*"%>
<%@ page import="org.uengine.util.UEngineUtil"%>
<%@ page import="org.metaworks.*"%>
<%@ page import="org.metaworks.web.*"%>
<%@ page import="org.metaworks.inputter.*"%>
<%@ page import="org.uengine.webservices.worklist.*"%>
<%@ page import="org.uengine.webservice.*"%>
<%@ page import="org.uengine.security.AclManager" %>
<%@ page import="org.uengine.contexts.*" %>

<%@include file="../common/getLoggedUser.jsp"%>
<%
	response.setContentType("text/html; charset=UTF-8");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

	String filePath = request.getParameter("filePath");

	boolean fileExist = false;
	FileInputStream formFile = null;
	try{
	     formFile = new FileInputStream(FormActivity.FILE_SYSTEM_DIR + filePath + ".log.xml");
	     fileExist = true;
	}catch(Exception e){
		
	}
	
	if(!fileExist){
		%>Empty!<%
	}
%>

<%	

	if(fileExist){
	     ByteArrayOutputStream bao = new ByteArrayOutputStream();
	     UEngineUtil.copyStream(formFile, bao );

	     %><textarea cols=150 rows=50><%=bao.toString()%></textarea><%
	}
%>
