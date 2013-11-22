<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>
<%@page import="org.uengine.util.export.UEngineArchive"%>
<%@page import="org.uengine.persistence.processdefinition.ProcessDefinitionDAO"%>

<%@page import="org.uengine.security.AclManager"%><jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
	request.setCharacterEncoding("utf-8");
    
	Map parameterMap = request.getParameterMap();

	String[] data_name = (String[]) parameterMap.get("data_name");
	String[] data_alias = (String[]) parameterMap.get("data_alias");
	String[] data_type = (String[]) parameterMap.get("data_type");
	String[] data_duplication = (String[]) parameterMap.get("data_duplication");
	String[] data_archiveFileName = (String[]) parameterMap.get("data_archiveFileName");
	String[] command = (String[]) parameterMap.get("command");
	String fileName = (String)request.getParameter("fileName");
	String rootFolder = (String)request.getParameter("definitionId");
	
	UEngineArchive ua = new UEngineArchive();
	for (int i=0; i<data_name.length; i++) {
		if(i==0)
			ua.setDefinitionList(data_name[i], data_alias[i], ""+i, "", data_type[i], data_archiveFileName[i], "", "", "", true);
		else
			ua.setDefinitionList(data_name[i], data_alias[i], ""+i, "", data_type[i], data_archiveFileName[i], "", "", "", false);
	}
	
	ProcessManagerRemote pm = null;
	try{
		pm = processManagerFactory.getProcessManager();

		String TEMP_DIRECTORY = GlobalContext.getPropertyString(
				"server.definition.path",
				"." + File.separatorChar + "uengine" + File.separatorChar + "definition" + File.separatorChar
			);
			
			if(!TEMP_DIRECTORY.endsWith("/") && !TEMP_DIRECTORY.endsWith("\\")){
				TEMP_DIRECTORY = TEMP_DIRECTORY + "/";
			}
			TEMP_DIRECTORY = TEMP_DIRECTORY + "temp" + File.separatorChar;
		
		String path = TEMP_DIRECTORY + "upload" + File.separatorChar + fileName;

		
		InputStream is = new BufferedInputStream(new FileInputStream(path));
		
		Vector newDefId = pm.importProcessDefinition(rootFolder,is, ua, command);
		
		AclManager dau =  AclManager.getInstance();
		for(int i=0; i< newDefId.size() ; i++){
			String defId = (String)newDefId.get(i);
			
			dau.setPermission(
					Integer.parseInt(defId),
					AclManager.ACL_FIELD_COM, loggedUserGlobalCom,
					new String[]{AclManager.PERMISSION_MANAGEMENT + ""}
			);
			
			ProcessTransactionContext tc = ((ProcessManagerBean)pm).getTransactionContext();
			ProcessDefinitionDAO procDef = (ProcessDefinitionDAO) tc.findSynchronizedDAO("bpm_procdef", "defid",defId , ProcessDefinitionDAO.class);
			procDef.setComCode(loggedUserGlobalCom);
		}
			
		pm.applyChanges();
%>
ProcessDefinition has been successfully imported.
<%
	} catch(Exception e){
%>
ProcessDefinition import failed.
<%		
		pm.cancelChanges();
	    e.printStackTrace();
	} finally{
	  	try{
			pm.remove();
		}catch(Exception ex){}
	}
	
%>
<script type="text/javascript">
	sendRedirectPage();
	
	function sendRedirectPage() {
		setTimeout("top.location.reload()", 3000);
	}
</script>
