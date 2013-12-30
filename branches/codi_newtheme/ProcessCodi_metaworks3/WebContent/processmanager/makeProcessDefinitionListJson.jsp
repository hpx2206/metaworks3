<%@page import="org.uengine.ui.tree.json.ProcessDefinitionListToJSON"%>
<%@page import="org.uengine.ui.tree.dao.ProcessDefinitionListDAO"%>

<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>
<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="org.uengine.ui.tree.model.Item"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="org.uengine.util.dao.DefaultConnectionFactory"%>
<%
	String parentDefId = request.getParameter("parentDefId");
	if (!UEngineUtil.isNotEmpty(parentDefId) || "continentRoot".equals(parentDefId)) {
		parentDefId = "-1";
	}
	
	SimpleTransactionContext st = new SimpleTransactionContext();
	
	try{
		//DataSource dataSource = DefaultConnectionFactory.create().getDataSource();
		
		ProcessDefinitionListDAO processDefinitionListDAO = new ProcessDefinitionListDAO(st);
		List<Item> processDefinitionList = null;
		if (loggedUserIsMaster) {
			//processDefinitionList = processDefinitionListDAO.findAllProcessDefinitions(loggedUserId);
			processDefinitionList = processDefinitionListDAO.selectAllProcessDefinitions(parentDefId);
		} else {
			//processDefinitionList = processDefinitionListDAO.findAllAuthorityProcessDefinitions(loggedUserId, loggedUserGlobalCom, AclManager.PERMISSION_EDIT);
			processDefinitionList = processDefinitionListDAO.selectProcessDefinitions(loggedUserId, loggedUserGlobalCom, parentDefId, AclManager.PERMISSION_EDIT);
		}
		ProcessDefinitionListToJSON makeProcessDefinitionListJson = new ProcessDefinitionListToJSON();
		//out.print(makeProcessDefinitionListJson.toJSON(processDefinitionList));
		out.print(makeProcessDefinitionListJson.toJSON(processDefinitionList, parentDefId));
		
	} finally{
		try {
			st.releaseResources();
		} catch (Exception e) {
		}
	}
%>