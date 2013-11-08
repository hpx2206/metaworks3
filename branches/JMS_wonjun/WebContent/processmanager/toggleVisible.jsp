<%@include file="../common/header.jsp"%>
<%@page import="javax.transaction.*"%>


<LINK href="../style/uengine.css" type=text/css rel=stylesheet>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%

	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	InitialContext context = new InitialContext();
	
	String pd = decode(request.getParameter("definitionId"));
	boolean isVisible = "true".equals(request.getParameter("visible"));
	isVisible = !isVisible;
	UserTransaction tx = (GlobalContext.useManagedTransaction ? (UserTransaction)context.lookup(GlobalContext.USERTRANSACTION_JNDI_NAME) : null);

	try{
		if(tx!=null) tx.begin();
	
		pm.setVisibleProcessDefinition(pd, isVisible);
		pm.applyChanges();
		
		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.commit();

%>
Folder has been successfully removed.<br>
<script type="text/javascript">
location.href = "index.jsp";
</script> 

<%
	}catch(Exception e){
		e.printStackTrace();
	
		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.rollback();
		throw e;
	}

%>