<%@include file="../common/header.jsp"%>

<LINK href="../style/uengine.css" type=text/css rel=stylesheet>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%

	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	
	String pd = request.getParameter("processDefinition");
	String newName = request.getParameter("newName");
	
	try{
		pm.renameProcessDefinition(pd, newName);
		pm.applyChanges();
	}catch(Exception e){
		pm.cancelChanges();
		throw e;
	}finally{
		pm.remove();
	}
%>
Definition name for '<%=pd%>' has been changed successfully.<br>

<script type="text/javascript">
location.href = "index.jsp";
</script> 