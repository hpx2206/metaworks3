<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<html>
<head>	
</head>

<body>
<%
	String strategyId = request.getParameter("strategyId");

	ProcessManagerRemote pm = processManagerFactory.getProcessManager();

	String processDefinition = decode(request.getParameter("processDefinition"));
	String procDefId = decode(request.getParameter("procDefId"));
	String procDefAlias = decode(request.getParameter("alias"));
	
	if(org.uengine.util.UEngineUtil.isNotEmpty(procDefId)){
		processDefinition = pm.getProcessDefinitionProductionVersion(procDefId);
	}
	if(org.uengine.util.UEngineUtil.isNotEmpty(procDefAlias)){
		processDefinition = pm.getProcessDefinitionProductionVersionByAlias(procDefAlias);
	}

	ProcessDefinition pd = null;
	ActivityReference initiatorHumanActivityReference;

	InitialContext context = new InitialContext();
	UserTransaction tx = (GlobalContext.useManagedTransaction ? (UserTransaction)context.lookup(GlobalContext.USERTRANSACTION_JNDI_NAME) : null);

	String currentTracTag = null;
	String currentTaskId = null;
	
	try{
		if(tx!=null) tx.begin();

		pd = pm.getProcessDefinition(processDefinition);
		initiatorHumanActivityReference = pm.getInitiatorHumanActivityReference(processDefinition);
			
		if(initiatorHumanActivityReference!=null && pd.isInitiateByFirstWorkitem()){
																   
			String initiatorHumanActivityTracingTag = initiatorHumanActivityReference.getAbsoluteTracingTag();
			HumanActivity initiatorHumanActivity = (HumanActivity)initiatorHumanActivityReference.getActivity();
			String tool = initiatorHumanActivity.getTool();
			
			String instId="";
			boolean isCompleteed=false;
			if(initiatorHumanActivity.isNotificationWorkitem()){
				Map genericContext = new HashMap();
				genericContext.put(HumanActivity.GENERICCONTEXT_CURR_LOGGED_ROLEMAPPING, loggedRoleMapping);
					
				pm.setGenericContext(genericContext);
				instId = pm.initializeProcess(processDefinition);
				pm.executeProcess(instId);

				ActivityInstanceContext  aic = pm.getProcessInstance(instId).getCurrentRunningActivity();

				if(aic !=null){
					Activity act = aic.getActivity();
					currentTracTag = act.getTracingTag();
					
					if (act instanceof TelnetNormalActivity) {
					   tool = ((TelnetNormalActivity)act).getTool();
					   currentTaskId = ((TelnetNormalActivity)act).getTaskIds(pm.getProcessInstance(instId))[0];
				    } else if (act instanceof HumanActivity) {
					   tool = ((HumanActivity)act).getTool();
					   currentTaskId = ((HumanActivity)act).getTaskIds(pm.getProcessInstance(instId))[0];
					}
				}else{
					isCompleteed=true;
				}
			}
						
			if(!isCompleteed){
					String url = "../wih/" + tool + "/index.jsp";
			
					Map parameterMap = initiatorHumanActivity.getParameterMap();
%>

<form name="notificationWorkitemAction" action="<%=request.getContextPath() %>/processparticipant/worklist/workitemHandler.jsp" method="post">
	<input type="hidden" name="taskId" value="<%=currentTaskId %>">
	<input type="hidden" name="tracingTag" value="<%=currentTracTag %>">
	<input type="hidden" name="instanceId" value="<%=instId %>">
	<input type="hidden" name="isViewMode" value="false">
	<input type="hidden" name="strategyId" value="<%=strategyId %>">
</form>

<form name="defaultAction" action="<%=url%>" method="POST">
<%			
					for(Iterator iter = parameterMap.keySet().iterator(); iter.hasNext();) {
						String key = (String)iter.next();
						String value = ""+parameterMap.get(key);
						value=value.replace('\"','\'');
						//System.out.println("1key:"+key+",value:"+value);
							%>
								<input type=hidden name="<%=key%>" value="<%=value%>">
							<%
					}
					Enumeration enumeration = request.getParameterNames();
					HashMap valueMap = new HashMap();
		 
					for(;enumeration.hasMoreElements();){
						String key = (String)enumeration.nextElement();
						String value = request.getParameter(key);
						//System.out.println("2key:"+key+",value:"+value);
		   
							%>
								<input type=hidden name="<%=key%>" value="<%=value%>">
							<%
					}

					if(!"".equals(instId)){
%>
						<input type=hidden value='<%=instId%>' name=instanceId>
<%
					}
%>
					<input type=hidden value='<%=processDefinition%>' name='processDefinition'>
					<input type=hidden value='<%=initiatorHumanActivityTracingTag%>' name='tracingTag'>
					<input type=hidden value='<%=initiatorHumanActivityTracingTag%>' name='initiatorHumanActivityTracingTag'>
					<input type=hidden value='yes' name='initiate'>
					<input type="hidden" name="strategyId" value="<%=strategyId %>" />
</form>
<%
			}else{
%>

Process completed with instance id [<%=instId%>]
<script type="text/javascript">
	setInterval("parent.close()", 2000);
</script>

<%
			}
			pm.applyChanges();			
		}else{
			String instId = pm.initializeProcess(processDefinition);
			//pm.executeProcess(instId);
			pm.applyChanges();
			
			%>Process has been intialized with instance id [<%=instId%>]<%
		}
		
		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.commit();
		
	}catch(Exception e){
		try{
			pm.cancelChanges();
		}catch(Exception ex){
		}
		
		if(tx!=null && tx.getStatus() != Status.STATUS_NO_TRANSACTION )
			tx.rollback();

		throw e;
	}finally{
		try{
			pm.remove();
		}catch(Exception ex){
		}
	}
%>

<script type="text/javascript">
<%
	if (currentTaskId != null) {
		out.print("document.notificationWorkitemAction.submit();");
	} else {
		out.print("document.defaultAction.submit();");
	}
%>
</script>

</body>