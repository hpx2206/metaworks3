<%@page contentType="text/xml; charset=UTF-8" import="org.uengine.contexts.*,org.uengine.kernel.*,org.uengine.processmanager.*"%><%
	response.setContentType("text/xml; charset=UTF-8");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
	<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
	
	String instanceId	= request.getParameter("instanceId");

	String result = "FAIL";

	ProcessManagerRemote pm = processManagerFactory.getProcessManager();

	try{
		ProcessInstance instance = pm.getProcessInstance(instanceId).createSnapshot();

		result = GlobalContext.serialize(instance, String.class);;
	}catch(Exception e){
		e.printStackTrace(response.getWriter());
		e.printStackTrace();
	}	
%><%=result%>