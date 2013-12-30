<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="../common/header.jsp"%>
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<% 
	request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    
    String defVerId = request.getParameter("defVerId");
    String tracingTag = request.getParameter("tracingTag");
    String instId = request.getParameter("instId");

    

    ProcessManagerRemote pm = processManagerFactory.getProcessManagerForReadOnly();
    
    try{
    	ProcessInstance instance = null;
    	ProcessDefinition def = null;
    	
    	if(UEngineUtil.isNotEmpty(instId)){
    		instance = pm.getProcessInstance(instId);
    	    def = instance.getProcessDefinition();
    	}else{
    	    def = pm.getProcessDefinition(defVerId);
    	}

    	Activity act = def.getActivity(tracingTag);
	    act = (Activity)act.clone();
	    
	    act.setParentActivity(null);
	    
	    String actInStr = GlobalContext.serialize(act, String.class);
%>

<%@page import="org.uengine.util.*" %>

<form action="activityChange.jsp" method="POST">
<input type=submit>
<textarea name="definition" cols=300 rows=50><%=actInStr%></textarea>
<input name="instId" type="hidden" value="<%=instId%>">
<input name="tracingTag" type="hidden" value="<%=tracingTag%>">
<input name="defVerId" type="hidden" value="<%=defVerId%>">

<input type=submit>
</form>


<%

	}catch(Exception e){
		throw e;
	}finally{
		try{pm.remove();}catch(Exception ex){}
	}

%>
