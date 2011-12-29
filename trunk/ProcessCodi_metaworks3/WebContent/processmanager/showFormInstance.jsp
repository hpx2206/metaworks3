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
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@include file="../common/getLoggedUser.jsp"%>

<form action="formInstanceChange.jsp" method="POST" target="_blank">
<textarea name="contents" cols=300 rows=50>
<%
	String filePath = request.getParameter("filePath");
	String instanceId = request.getParameter("instanceId");
	String variableName = request.getParameter("variableName");

	FileInputStream formFile = null;
	ByteArrayOutputStream bao = null;
	try{
		formFile = new FileInputStream(FormActivity.FILE_SYSTEM_DIR + filePath);
		bao = new ByteArrayOutputStream();
		UEngineUtil.copyStream(formFile, bao);
		out.print(bao.toString("UTF-8"));
	} catch(Exception e){
		out.print("Empty!");
	} finally {
		if (formFile != null) try { formFile.close(); } catch(Exception e) {}
		if (bao != null) try { bao.close(); } catch(Exception e) {}
	}
%>
</textarea>

<input name="instanceId" value="<%=instanceId%>">
<input name="variableName" value="<%=variableName%>">

<input type=submit>
</form>
