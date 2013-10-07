<%@include file="../common/header.jsp"%>
<%@page import="javax.transaction.*"%>
<%@page import="org.uengine.security.AclManager"%>

<LINK href="../style/uengine.css" type=text/css rel=stylesheet>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%

	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	InitialContext context = new InitialContext();
	
	String pd = decode(request.getParameter("folder"));
	
	UserTransaction tx = (GlobalContext.useManagedTransaction ? (UserTransaction) context.lookup(GlobalContext.USERTRANSACTION_JNDI_NAME) : null);

	try {
		if (tx != null) tx.begin();
	
		pm.removeFolder(pd);
		pm.applyChanges();
		
		AclManager acl = AclManager.getInstance();
		acl.removePermissionForDefinistion(Integer.parseInt(pd));
		
		if (tx != null && tx.getStatus() != Status.STATUS_NO_TRANSACTION) {
			tx.commit();
		}
		
%>
		Folder has been successfully removed.<br>
		<%=pd %>
		<script type="text/javascript">
			window.top.location.href = "index.jsp";
		</script> 
<%
	} catch(Exception e) {
		e.printStackTrace();
	
		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.rollback();
		throw e;
	}

%>