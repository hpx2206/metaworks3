<%@include file="../common/header.jsp"%>
<%@page import="javax.transaction.*"%>

<LINK href="../style/uengine.css" type=text/css rel=stylesheet>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%

	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	InitialContext context = new InitialContext();
	
	UserTransaction tx = (GlobalContext.useManagedTransaction ? (UserTransaction)context.lookup(GlobalContext.USERTRANSACTION_JNDI_NAME) : null);

	try{
		if(tx!=null) tx.begin();
	
		String pi = decode(request.getParameter("processInstance"));
		pm.executeProcess(pi);
		pm.applyChanges();
		
		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.commit();

%>
Process instance '<%=pi%>' has been successfully started.<p>

[<a href="processInstanceListDetail.jsp">back</a>]



<%
	}catch(Exception e){
		try{
			pm.cancelChanges();
		}catch(Exception ex){			
		}
	
		try{
			tx.rollback();
		}catch(Exception ex){
		}
		
		throw e;
	}finally{
		try{
			pm.remove();
		}catch(Exception ex){
		}
	}

%>