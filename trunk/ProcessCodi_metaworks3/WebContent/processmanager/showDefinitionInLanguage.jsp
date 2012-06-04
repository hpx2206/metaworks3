<%@page contentType="text/xml; charset=UTF-8" import="javax.naming.Context,javax.naming.InitialContext,javax.naming.NamingException,javax.rmi.PortableRemoteObject,org.uengine.kernel.*,org.uengine.processmanager.*"%><%
	response.setContentType("text/xml; charset=UTF-8");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

%><jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%

	ProcessManagerRemote pm = null;
	String def = null;
	
	try{
		pm = processManagerFactory.getProcessManagerForReadOnly();
		
		String pd = request.getParameter("processDefinition");
		String versionId = request.getParameter("versionId");
		String language = request.getParameter("language");
		String instanceId = request.getParameter("instanceId");
		
		String userId = request.getParameter("userId");

		request.getSession().setAttribute("userId", userId);

		org.uengine.codi.mw3.CodiClassLoader.initClassLoader();


		//case that find by definition id
		if(versionId==null && pd!=null){
			
		}
		
		//TODO we assume that this will be called by Process Designer only.
		if(instanceId!=null){
			def = pm.getProcessDefinitionWithInstanceIdWithoutInheritance(instanceId, language);
		}else if(versionId==null && pd!=null){
			versionId = pm.getProcessDefinitionProductionVersion(pd);
			def = pm.getProcessDefinitionWithoutInheritance(versionId, language);
		}else{
			def = pm.getProcessDefinitionWithoutInheritance(versionId, language);
		}	
	}finally{
		if(pm != null) try{ pm.remove(); } catch(Exception e){}
	}

%><%=def%>