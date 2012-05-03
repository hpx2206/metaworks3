<%@page contentType="text/xml; charset=UTF-8" import="org.uengine.contexts.*,org.uengine.kernel.*,org.uengine.processmanager.*,javax.transaction.*,javax.naming.InitialContext"%><%
	response.setContentType("text/xml; charset=UTF-8");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%><%!
	public String decode(String source) throws Exception{
			if(source==null)
				return null;
			return new String(source.getBytes("8859_1"), "UTF-8");
	}

%><jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%
	
	String instanceId	= request.getParameter("instanceId");
	String definitionXML= decode(request.getParameter("definitionXML"));

	String result = "FAIL";

	InitialContext context = new InitialContext();
	
	UserTransaction tx = (GlobalContext.useManagedTransaction ? (UserTransaction)context.lookup(GlobalContext.USERTRANSACTION_JNDI_NAME) : null);
	ProcessManagerRemote pm = processManagerFactory.getProcessManager();

	try{
		if(tx!=null) tx.begin();
		pm.changeProcessDefinition(instanceId, definitionXML);
		pm.applyChanges();
		result = "OK";
		
		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.commit();

	}catch(Exception e){
		e.printStackTrace();
		
		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.rollback();
	}	
%><%=result%>