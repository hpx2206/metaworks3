<%@page import="org.uengine.persistence.processdefinition.*,org.uengine.persistence.processdefinitionversion.*,javax.naming.InitialContext,javax.naming.NamingException,javax.rmi.PortableRemoteObject,org.uengine.kernel.*,org.uengine.processmanager.*,java.io.*,org.uengine.util.UEngineUtil"%><%
	TransactionContext st = new SimpleTransactionContext();

	try{
		ProcessDefinitionDAOType pddt = ProcessDefinitionDAOType.getInstance(st);
	
		if(UEngineUtil.isNotEmpty(belongingDefinitionId)){
			ProcessDefinitionVersionDAO versionDAO = pddt.getAllVersions(Long.parseLong(belongingDefinitionId));
			String formTemplatePathRoot=pageContext.getServletContext().getRealPath("html/uengine-web/forms/");
			
			File lastFormTemplate = null;
			while(versionDAO.next()){
				Number versionId = versionDAO.getDefVerId();
				
				File dir = new File(formTemplatePathRoot + "/" + versionId);
				
				if(dir.exists()){
					lastFormTemplate = dir;
					break;
				}
			}
			
			if(lastFormTemplate!=null){
				File newDir = new File(formTemplatePathRoot + "/" + defVerId.split("@")[0]);
		
				if(!newDir.exists() && !newDir.mkdir()) throw new UEngineException("couldn't create a directory for creating new form template: " + newDir.getAbsolutePath());
				
				File[] dirForTracingTags = lastFormTemplate.listFiles();
				for(int i=0; i<dirForTracingTags.length; i++){
					File dirForTracingTag = dirForTracingTags[i];
					
					if(!dirForTracingTag.isDirectory()) continue;
					
					File newDirForTracingTag = 	new File(newDir, dirForTracingTag.getName());
					if(!newDirForTracingTag.exists()){
						if(!newDirForTracingTag.mkdir()) throw new UEngineException("couldn't create a directory for creating new form template: " + newDirForTracingTag.getAbsolutePath());
					
						File[] files = dirForTracingTag.listFiles();
						for(int j=0; j<files.length; j++){
							File theFile = files[j];
							File newFile = new File(newDirForTracingTag, theFile.getName());
							
							InputStream inputStream = new FileInputStream(theFile);
							UEngineUtil.copyStream(inputStream, new FileOutputStream(newFile));
						}
					}
				}
			}
		}
		
	}catch(Exception e){
		throw e;
	}finally{
		st.releaseResources();
	}
	
%>