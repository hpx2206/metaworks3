<%@include file="../common/header.jsp"%>

<meta http-equiv='refresh' content='3; url=index.jsp'>
<link href="../style/uengine.css" type="text/css" rel="stylesheet" />
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	
	String pd = decode(request.getParameter("processDefinition"));
	//String fn = decode(request.getParameter("folderName"));
	String parent = decode(request.getParameter("parentFolder"));
	
	try{
		pm.moveFolder(pd, parent);
		pm.applyChanges();
	}catch(Exception e){
		pm.cancelChanges();
		e.printStackTrace(response.getWriter());
		e.printStackTrace();
		throw e;
	}
%>
'<%=pd%>' has been successfully moved.
<br /><br />
[<a href="index.jsp">back</a>]