<%@page import="javax.naming.Context,javax.naming.InitialContext,javax.naming.NamingException,javax.rmi.PortableRemoteObject,org.uengine.kernel.*,org.uengine.processmanager.*"%><%!
	public String decode(String source) throws Exception{
			if (source == null)
				return null;
			return new String(source.getBytes("8859_1"), "UTF-8");
	}

%><jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%

	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	
	String definition 		= decode(request.getParameter("definition"));
	String version 			= decode(request.getParameter("version"));
	String definitionName 	= decode(request.getParameter("definitionName"));
	String definitionAlias 	= decode(request.getParameter("definitionAlias"));
	String savingFolder		= decode(request.getParameter("folderId"));
	String description 		= decode(request.getParameter("description"));
	String objectType 		= decode(request.getParameter("objectType"));
	String belongingDefinitionId
								= request.getParameter("defId");
	try{
		String defVerId = pm.addProcessDefinition(definitionName, Integer.parseInt(version), description, false, definition, savingFolder, belongingDefinitionId, definitionAlias, objectType);
		pm.applyChanges();
		%>Object has been successfully saved with id [<%=defVerId%>]<%
	}catch(Exception e){
		pm.cancelChanges();
		e.printStackTrace(response.getWriter());
		e.printStackTrace();
	}finally{
		try{
			pm.remove();
		} catch(Exception e) {}
	}
%>