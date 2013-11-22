<%@page contentType="text/xml; charset=UTF-8" 
	    import="java.lang.reflect.Method,
	            java.io.*,
	            java.util.*,
	            org.uengine.kernel.*,
	            org.uengine.processmanager.*,
                au.id.jericho.lib.html.*"%>
<%
	response.setContentType("text/xml; charset=UTF-8");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%><jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
	//load up the formfield
	String versionId = request.getParameter("versionId");	
	ProcessManagerRemote pm = null;
	String html = "";
	try{
		pm = processManagerFactory.getProcessManagerForReadOnly();
		html = pm.getResource(versionId);
	
	}finally{
		try{pm.remove();}catch(Exception e){}
	}
%><%=html%>
