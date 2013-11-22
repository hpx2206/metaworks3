<%@include file="../common/header.jsp"%>
<%@page import="javax.transaction.*"%>
	
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
	String clsName = decode(request.getParameter("dataClassName"));
	Class outputCls = org.uengine.kernel.GlobalContext.getComponentClass(clsName);

	ObjectInstance rec = (ObjectInstance)WebUtil.createInstance(new ObjectType(outputCls), request.getParameterMap(), "", null);
	Object value = rec.getObject();
	
	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	
	InitialContext context = new InitialContext();
	UserTransaction tx = (GlobalContext.useManagedTransaction ? (UserTransaction)context.lookup(GlobalContext.USERTRANSACTION_JNDI_NAME) : null);

	try{
		if(tx!=null) tx.begin();
		
		pm.setProcessVariable(decode(request.getParameter("instanceId")), "", decode(request.getParameter("variableName")), (java.io.Serializable)value);
		pm.applyChanges();

		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.commit();

	}catch(Exception e){
		e.printStackTrace();
		
		pm.cancelChanges();
	
		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.rollback();
		throw e;
	} finally {
		pm.remove();
	}

%>

changed value = '<%=value%>'<br>
Value has been successfully changed.