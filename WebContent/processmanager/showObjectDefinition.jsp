<%@page contentType="text/xml; charset=UTF-8" import="java.lang.reflect.Method,java.io.*,java.util.*,org.uengine.kernel.*,org.uengine.processmanager.*"%>
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
response.setContentType("text/xml; charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0); //prevents caching at the proxy server

ProcessManagerRemote pm = null;
	
try{
	pm = processManagerFactory.getProcessManager();

	String objDefId = request.getParameter("objDefId");

	out.println(pm.getResource(objDefId));
}finally{
	try{
		pm.remove();
	}catch(Exception ex){}
}
%>
