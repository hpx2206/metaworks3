<%@include file="../common/header.jsp"%>
<%@page import="javax.transaction.*"%>
<%@page import="org.uengine.kernel.*"%>
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	
	String tracingTag = decode(request.getParameter("tracingTag"));
	String pi = decode(request.getParameter("instanceId"));
	
	InitialContext context = new InitialContext();
	if(pi != null){
		UserTransaction tx = (GlobalContext.useManagedTransaction ? (UserTransaction)context.lookup(GlobalContext.USERTRANSACTION_JNDI_NAME) : null);

		try{
			if(tx!=null) tx.begin();		
	
			ProcessInstance processInstance = pm.getProcessInstance(pi);
			SubProcessActivity spa = (SubProcessActivity)processInstance.getProcessDefinition().getActivity(tracingTag);
			spa.refreshMultipleInstance(processInstance);
			pm.applyChanges();

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
			//throw e;
		}finally{
			try{
				pm.remove();	
			}catch(Exception ex){
			}
		}
	}
	
	String returnPage = decode(request.getParameter("returnPage"));
	
	if(returnPage!=null){
		%><body onload="location='<%=returnPage%>?instanceId=<%=pi%>'"><%
	}
%>