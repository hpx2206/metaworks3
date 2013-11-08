<%@include file="../common/header.jsp"%>
<%@page import="javax.transaction.*"%>
<html>
<head>
<LINK href="../style/uengine.css" type=text/css rel=stylesheet>
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
	ProcessManagerRemote pm = null;
	InitialContext context = new InitialContext();

	String pi = request.getParameter("instanceId");

//	UserTransaction tx = (GlobalContext.useManagedTransaction ? (UserTransaction)context.lookup(GlobalContext.USERTRANSACTION_JNDI_NAME) : null);
	UserTransaction tx = null;
	
	try
	{
		pm = processManagerFactory.getProcessManager();
		if(tx!=null) tx.begin();
		
		pm.removeProcessInstance(pi);
		pm.applyChanges();
		
		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.commit();

%>

</head>
<body>
Process instance '<%=pi%>' has been successfully removed.<p>

<%
	}
	catch(Exception e)
	{
		e.printStackTrace();
	
		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.rollback();
		throw e;
	}
	finally
	{
		if (pm != null) { try{ pm.remove(); } catch(Exception e) {} }
	}

%>
</body>
<script type="text/javascript">
//history.go(-1); not operation in chrome series browser / in paging site not operation.
window.location.href = document.referrer; 
</script>
</html>