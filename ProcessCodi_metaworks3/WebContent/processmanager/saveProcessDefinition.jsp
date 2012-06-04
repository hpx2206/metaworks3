<%@page contentType="text/xml; charset=UTF-8" import="javax.naming.Context,javax.naming.InitialContext,javax.naming.NamingException,javax.rmi.PortableRemoteObject,org.uengine.kernel.*,org.uengine.processmanager.*"%><%!
	public String decode(String source) throws Exception{
			if(source==null)
				return null;
			return new String(source.getBytes("8859_1"), "UTF-8");
	}

%><jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%

	ProcessManagerRemote pm = processManagerFactory.getProcessManager();

	response.setContentType("text/xml; charset=UTF-8");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

	try{
		String definition 		= decode(request.getParameter("definition"));
		String version 			= decode(request.getParameter("version"));
		String definitionName 	= decode(request.getParameter("definitionName"));
		String savingFolder		= decode(request.getParameter("folderId"));
		String description 		= decode(request.getParameter("description"));
		String alias 			= decode(request.getParameter("alias"));
		String author 			= decode(request.getParameter("author"));
		boolean autoProduction = "true".equals(request.getParameter("autoProduction"));
		String belongingDefinitionId
							= request.getParameter("defId");
		String objType 			= decode(request.getParameter("objType"));
							
					
		org.uengine.codi.mw3.CodiClassLoader.initClassLoader();

		//ProcessDefinition sampleProc = new ProcessDefinition();
		//sampleProc.setName("xxxxx");
		
	    //definition = GlobalContext.serialize(sampleProc, String.class);
		
	
		String defVerId = pm.addProcessDefinition(definitionName, Integer.parseInt(version), description, false, definition, savingFolder, belongingDefinitionId, alias, objType);
		
/* 		if(autoProduction)
			pm.setProcessDefinitionProductionVersion(defVerId);
 */		
		pm.applyChanges();

%><%@include file="../common/callback/afterSaveProcessDefinition.jsp"%>OK@<%=defVerId%><%
	}catch(Exception e){
		e.printStackTrace();
		if(pm!=null)
			pm.cancelChanges();
%><%=e.getMessage()%><%
	}finally{
		pm.remove();
	}
%>