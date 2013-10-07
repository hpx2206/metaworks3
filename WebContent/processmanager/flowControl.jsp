<%@include file="../common/header.jsp"%>
<%@page import="javax.transaction.*"%>
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	
	String tracingTag = decode(request.getParameter("tracingTag"));
	String pi = decode(request.getParameter("instanceId"));
	String command = decode(request.getParameter("command"));
	StringBuilder debugInfo = null;
	
	InitialContext context = new InitialContext();
	if(pi != null){
		UserTransaction tx = (GlobalContext.useManagedTransaction ? (UserTransaction)context.lookup(GlobalContext.USERTRANSACTION_JNDI_NAME) : null);

		try{
			if(tx!=null) tx.begin();		
	
			HashMap genericContext = new HashMap();
			genericContext.put("servlet", this);
			genericContext.put("request", request);
			genericContext.put("response", response);
			pm.setGenericContext(genericContext);
			
			pm.flowControl(command, pi, tracingTag);
			pm.applyChanges();
			
			if(pm instanceof ProcessManagerBean){
				ProcessManagerBean pmb = (ProcessManagerBean)pm;
				debugInfo = pmb.getTransactionContext().getDebugInfo();
			}

			if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
				tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			try{
				pm.cancelChanges();	
			}catch(Exception ex){
			}
			
			if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
				try{
					tx.rollback();
				}catch(Exception ex){}
			throw e;
		}finally{
			try{
				pm.remove();	
			}catch(Exception ex){
			}
		}
	}
	
	%>Flow control job has been successfully done! <br>
	<%
	String returnPage = decode(request.getParameter("returnPage"));
	
	if(returnPage!=null){
		%><a href='<%=returnPage%>?instanceId=<%=pi%>'><b>return page</b></a><%
	} 
	%>
	
	<textarea rows=80 cols=150><%=debugInfo%></textarea><p>
