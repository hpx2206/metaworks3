<%@include file="../common/header.jsp"%>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />

<%
	ProcessManagerRemote pm = processManagerFactory.getProcessManager();

	
	String pd = decode(request.getParameter("processDefinition"));
	try{
		pm.setProcessDefinitionProductionVersion(pd);
		pm.applyChanges();
	}catch(Exception e){
		pm.cancelChanges();
		e.printStackTrace(response.getWriter());
		e.printStackTrace();
		throw e;
	}
	
%>

<meta http-equiv='refresh' content='3; url=viewProcessFlowChart.jsp?processDefinitionVersionID=<%=pd%>'>
<link href="../style/uengine.css" type="text/css" rel="stylesheet" />
Process definition '<%=pd%>' is set to production version.<br>
