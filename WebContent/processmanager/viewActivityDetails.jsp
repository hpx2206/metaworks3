<%@include file="../common/header.jsp"%>

<html>
<head>
<title>Activity Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<LINK href="../style/uengine.css" type=text/css rel=stylesheet>
<LINK rel="stylesheet" href="../style/portlet.css" type="text/css"/>	
<LINK rel="stylesheet" href="../style/portal.css" type="text/css"/>	
<LINK rel="stylesheet" href="../style/groupware.css" type="text/css"/>
<LINK REL="stylesheet" type="text/css" href="../css/common.css">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr height="1">
		<td align="center" colspan="3" height="1" class="bgcolor_head_underline"></td>
	</tr>			


		
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" /><%

	ProcessManagerRemote pm = processManagerFactory.getProcessManager();
	
	String instanceID = decode(request.getParameter("instanceID"));
	String processDefinition = decode(request.getParameter("processDefinition"));
	String tracingTag = decode(request.getParameter("tracingTag"));
	
	Hashtable map = null;
	if(instanceID!=null && !instanceID.equals("null")){
		map = pm.getActivityInstanceDetails(instanceID, tracingTag);
	}else if(processDefinition!=null){
		map = pm.getActivityDetails(processDefinition, tracingTag);
	}
	
	if(map!=null)	 
	for(Iterator iter = map.keySet().iterator(); iter.hasNext(); ){
		String key = (String)iter.next();
%>
	<tr height="30" >
		<td width="50" align=left bgcolor="f4f4f4">
			&nbsp;<font size=-2> <%=key%> </font>
		</td>
		<td width="10"></td>
		<td width="*" align=left> <%=map.get(key)%> </td>
	</tr>
	<tr height="1">
		<td align="center" colspan="3" height="1" class="bgcolor_head_underline"></td>
	</tr>			

	
<%		
	}
%>

</table>