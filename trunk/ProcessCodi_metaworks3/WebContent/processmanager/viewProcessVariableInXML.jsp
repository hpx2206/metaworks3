<%@page contentType="text/xml; charset=UTF-8" import="javax.naming.Context,javax.naming.InitialContext,javax.naming.NamingException,javax.rmi.PortableRemoteObject,org.uengine.kernel.*,org.uengine.processmanager.*"%><%
	response.setContentType("text/xml; charset=UTF-8");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

%>
<%@include file="../common/headerMethods.jsp"%>
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%

	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	
	String pi = request.getParameter("instanceId");
	String variableName = decode(request.getParameter("variableName"));
	
	String value = pm.getProcessVariableInXML(pi, "", variableName);

%><%=value%>