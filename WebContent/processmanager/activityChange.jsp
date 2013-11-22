<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="../common/header.jsp"%>
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<% 
	request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    
    String defVerId = request.getParameter("defVerId");
    String tracingTag = request.getParameter("tracingTag");
    String instId = request.getParameter("instId");
    String definition = request.getParameter("definition");

    

    ProcessManagerRemote pm = processManagerFactory.getProcessManager();
    
    try{
	    ProcessDefinition def = pm.getProcessDefinition(defVerId);
	    
	    Activity existingAct = def.getActivity(tracingTag);
	    Activity act = (Activity)GlobalContext.deserialize(definition, Activity.class);

	    ComplexActivity complexAct = (ComplexActivity)existingAct.getParentActivity();
	    
	    int pos = complexAct.getChildActivities().indexOf(existingAct);
	    
	    complexAct.removeChildActivity(existingAct);
	    complexAct.addChildActivity(act, pos, false);
	    
	    pm.changeProcessDefinition(instId, def);
	    
	    pm.applyChanges();
%>

<%@page import="org.uengine.util.*" %>

Successfully Saved.

<a href="activityChangeForm.jsp?instId=<%=instId%>&tracingTag=<%=tracingTag%>&defVerId=<%=defVerId%>">back</a>

<%
	}catch(Exception e){
		pm.cancelChanges();
		
		throw e;
	}finally{
		try{pm.remove();}catch(Exception ex){}
	}

%>
