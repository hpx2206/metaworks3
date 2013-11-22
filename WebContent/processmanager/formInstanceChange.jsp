<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="../common/header.jsp"%>
<%@page import="java.text.SimpleDateFormat" %>
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<% 
	request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    
    String content = request.getParameter("contents");
    String instanceId = request.getParameter("instanceId");
    String variableName = request.getParameter("variableName");   

    ProcessManagerRemote pm = processManagerFactory.getProcessManagerForReadOnly();
    String filePath = null;
    
    try{
    	
    	HashMap valueMap = (HashMap)GlobalContext.deserialize(content);
    	
    	ProcessInstance instance = null;
    	ProcessDefinition def = null;  	
    	
		instance = pm.getProcessInstance(instanceId);
		ProcessDefinition definition = instance.getProcessDefinition();
		ProcessVariable variable = definition.getProcessVariable(variableName);
 	
		HtmlFormContext formCtx = (HtmlFormContext)instance.get(variableName);
		
		if(formCtx.getFilePath()==null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS", Locale.KOREA);
			
			filePath = UEngineUtil.getCalendarDir();
			File dirToCreate = new File(FormActivity.FILE_SYSTEM_DIR + filePath);
			dirToCreate.mkdirs();
		
			String datePrefix = sdf.format(new Date());
			String fileName = instance.getInstanceId() +"_"+datePrefix + ".xml";
			File newFile = new File(FormActivity.FILE_SYSTEM_DIR + filePath+"/"+fileName);
			FileOutputStream fos = new FileOutputStream(newFile);
			GlobalContext.serialize(valueMap, fos, HashMap.class);
			fos.close();
			
			HtmlFormContext formDefInfo = (HtmlFormContext)variable.getDefaultValue();
			String[] formDefID = formDefInfo.getFormDefId().split("@");
			String formDefinitionVersionId = (String) valueMap.get("formdefinitionversionid");
			if(!UEngineUtil.isNotEmpty(formDefinitionVersionId))formDefinitionVersionId= formDefID[1];
		
			
			HtmlFormContext newFormCtx = new HtmlFormContext();
			newFormCtx.setFilePath(filePath+"/"+fileName);
			newFormCtx.setFormDefId(formDefID[0] + "@" + formDefID[1]);
			newFormCtx.setValueMap(valueMap);
			
			filePath = newFormCtx.getFilePath();
			
			variable.set(instance, "", newFormCtx);
		}else{
		
			FileOutputStream fos = null;
			fos = new FileOutputStream(FormActivity.FILE_SYSTEM_DIR + formCtx.getFilePath());			
			GlobalContext.serialize(valueMap, fos, HashMap.class);
		}
    	
		pm.applyChanges();
		
	   %>

<%@page import="org.uengine.util.*" %>

Successfully Saved: <%= filePath%>


<%
	}catch(Exception e){
		pm.cancelChanges();
		
		throw e;
	}finally{
		try{pm.remove();}catch(Exception ex){}
	}

%>
