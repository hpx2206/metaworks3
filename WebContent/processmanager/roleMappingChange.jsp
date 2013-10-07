<%@include file="../common/header.jsp"%>
<%@page import="javax.transaction.*"%>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%
	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	InitialContext context = new InitialContext();

	//	WebInputter inputterForRoleMapping = (WebInputter)ObjectType.getDefaultInputter(RoleMapping.class); 
	//	RoleMapping roleMapping = (RoleMapping)inputterForRoleMapping.createValueFromHTTPRequest(request.getParameterMap(), "", "value", null);

	String endpoints = request.getParameter("endpoint");
	String[] endpoint = endpoints.split(";");

	RoleMapping roleMapping = RoleMapping.create();

	for (int i = 0; i < endpoint.length; i++) {
		roleMapping.setEndpoint(endpoint[i]);
		
		if ( i != endpoint.length - 1)
			roleMapping.moveToAdd();
	}

	roleMapping.setName(request.getParameter("roleName"));

	UserTransaction tx = (GlobalContext.useManagedTransaction ? (UserTransaction) context
			.lookup(GlobalContext.USERTRANSACTION_JNDI_NAME)
			: null);

	try {
		if (tx != null)
			tx.begin();

		//pm.putRoleMapping(request.getParameter("instanceId"), roleMapping);
		pm.delegateRoleMapping(request.getParameter("instanceId"), roleMapping);
		
		String orgRoleName = request.getParameter("orgRoleName");
		if (UEngineUtil.isNotEmpty(orgRoleName)) {
			RoleMapping orgRoleMapping = (RoleMapping)roleMapping.clone();
			orgRoleMapping.setName(orgRoleName);
			pm.delegateRoleMapping(request.getParameter("instanceId"), orgRoleMapping);
		}
		
		pm.applyChanges();

		if (tx != null
				&& tx.getStatus() != Status.STATUS_NO_TRANSACTION)
			tx.commit();
		
%>
Role mapping has been successfully changed.
<%
	} catch (Exception e) {
		e.printStackTrace();

		if (tx != null
				&& tx.getStatus() != Status.STATUS_NO_TRANSACTION)
			tx.rollback();
		throw e;
	}
%>
<script type="text/javascript">
	sendRedirectPage();
	
	function sendRedirectPage() {
		setTimeout("windowClose()", 1500);
	}
	
	function windowClose(){
		opener.location.reload();
		window.close();
	}
</script>