<%@include file="../common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<LINK href="../style/uengine.css" type=text/css rel=stylesheet>
<LINK rel="stylesheet" href="../style/portlet.css" type="text/css"/>	
<LINK rel="stylesheet" href="../style/portal.css" type="text/css"/>	
<LINK rel="stylesheet" href="../style/groupware.css" type="text/css"/>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />


<%
ProcessManagerRemote pm = null;
	
try{
	pm = processManagerFactory.getProcessManagerForReadOnly();

	String pd = decode(request.getParameter("objectDefinitionId"));
	String pdVer = decode(request.getParameter("processDefinitionVersionID"));
	String folder = decode(request.getParameter("folder"));

	/***********************************************************************/
	/*                            Get version list                         */
	/***********************************************************************/

	ProcessDefinitionRemote[] arrPdr = null;

	arrPdr = pm.findAllVersions(pd);
	if(arrPdr != null){
		String versionID = null;
		int version = -1;
		for(int i=0; i<arrPdr.length; i++){
			versionID = arrPdr[i].getId();
			version = arrPdr[i].getVersion();
			//out.println("versionID=["+versionID+"]");
			//out.println("version=["+version+"]");
			//out.println("isProduction=["+arrPdr[i].isProduction()+"]");
			if(arrPdr[i].isProduction()){
				if( pdVer == null || "".equals(pdVer) || "null".equals(pdVer)){
					pdVer = versionID;
				}
			}
		}
		if( pdVer == null || "".equals(pdVer) || "null".equals(pdVer)){
			pdVer = versionID;
		}
	}
	
	ProcessDefinitionRemote pdr;	

	pdr = pm.getProcessDefinitionRemote(pdVer);
		
	String title = pdr.getName().getText();
		
	String belongingDefId = pdr.getBelongingDefinitionId();
%>

<script>
	
	function removeDefinition(definition){
		answer = confirm("There may be a running instance with this process definition.\nAre you sure to remove?");
		if(answer){
			location="removeProcessDefinition.jsp?processDefinition=" + definition;
		}
	}
	
	function makeProduction(){
		location="makeProduction.jsp?processDefinition=<%=pdVer.replace(' ', '+')%>";
	}

	function saveRule(){
		document.actionForm.submit();
	}

	function saveRuleAsNewVersion(){
		document.actionForm.version.value = <%=(pdr.getVersion()+1)%>;	
		document.actionForm.submit();
	}
	
	function refresh(){
		var pdv = document.all.processDefinitionVersionID.value;
		location=("?objectDefinitionId=<%=pd%>&folder=<%=folder%>&processDefinitionVersionID=" + pdv);
	}
	
	function openBrWindow(theURL,winName,w,h,l,t,scroll,status) {
		var argLen = openBrWindow.arguments.length;
		var features;
		if(argLen==8) features = "width="+w+",height="+h+",left="+l+",top="+t+",scrollbars="+scroll+",status="+status;
		if(argLen==7) features = "width="+w+",height="+h+",left="+l+",top="+t+",scrollbars=yes";
		if(argLen==6) features = "width="+w+",height="+h+",left="+l+",top="+t;
		if(argLen==4) {
			var winLeft = (1024-w)/2;
			var winTop = (760-h)/2;
			features = "width="+w+",height="+h+",left="+winLeft+",top="+winTop;
		}
		if(argLen==3) features = w;
		var w = window.open(theURL,winName,features);
		w.focus();
		return w;
	}
	
	function uploadXLS(){
	    if(document.uploadFrm.fileName.value=='') {
	    	alert("Check FileName...... ");
	    	return;
	    }
		openBrWindow('','DSTable','680','300','','','true','true');
		document.uploadFrm.target='DSTable';
		document.uploadFrm.submit();
	}	
</script>


</head>

<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red" leftmargin="5" topmargin="0" marginwidth="0" marginheight="0" onload="drawLoopLines()">

<table border="0" cellpadding="0" cellspacing="0" width="100%" xmlns>
	<tr>
		<td height="28" valign="bottom">
			<p><img src="../images/icon_root.gif" align="middle" width="10" height="11" border="0"> 
			
			<b>Object Information</b>
		</td>
	</tr>
	<tr>
		<td height="1" class="path_underline"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="10"></td>
	</tr>
</table>

<%
	if(pdVer!=null){
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">		
<form name=actionForm action="saveObjectDefinition.jsp" method=POST>
	<tr>
		<td align="right" valign="center">
					<input value="Save" type=button onclick="saveRule()">
					<input value="Improve" type=button onclick="saveRuleAsNewVersion()">
					<input value="Retire" type=button onclick="removeDefinition('<%=pdVer%>')">
<%	
		if(pdr.isProduction()) {
%>
					<font color=red>[This version is production]</font>
<%
		} else {
%>
					<input type=button valign="center" value="Set as production" onclick="javascript:makeProduction()">
<%
		}
%>
		</td>
	</tr>

</table>
<%
	}
%>
		
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="5"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr height="1">
		<td align="center" height="1" class="bgcolor_head_upperline"></td>
	</tr>
	<tr height="5">
		<td align="center" height="5" class="bgcolor_head"></td>
	</tr>
	<tr height="1">
		<td align="center" height="1" class="bgcolor_head_underline"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr height="1">
		<td align="center" colspan="3" height="1" class="bgcolor_head_underline"></td>
	</tr>			
	
	<tr height="30" >
		<td width="150" align=left bgcolor="f4f4f4">
			&nbsp;<b>Definition Version</b>
		</td>
		<td width="10"></td>
		<td width="*" align=left>
			<%=pdr.getName().getText()%> (Version: <%=pdr.getVersion()%>)
		</td>
	</tr>
	<tr height="1">
		<td align="center" colspan="3" height="1" class="bgcolor_head_underline"></td>
	</tr>				
<%
	if(arrPdr != null){
%>	<tr height="30" >
		<td width="150" align=left bgcolor="f4f4f4">
			&nbsp;<b>Version</b>
		</td>
		<td width="10"></td>
		<td width="*" align=left>
			<select name="processDefinitionVersionID" id="processDefinitionVersionID" onchange="refresh();">	
<%	
			String versionID = null;
			String version = "-1";
			for(int i=0; i<arrPdr.length; i++){
				versionID = arrPdr[i].getId();
				version = ""+arrPdr[i].getVersion() + (arrPdr[i].isProduction() ? "*":"");
%>
				<option value="<%=versionID%>" <%=(versionID.equals(pdVer))?"selected":""%>><%=version%></option>
<%
			}
%>
			</select>
		</td>
	</tr>
	<tr height="1">
		<td align="center" colspan="3" height="1" class="bgcolor_head_underline"></td>
	</tr>
<%
	}

	if (pdVer!=null) { 
		String desc = (pdr.getDescription()!=null ? pdr.getDescription().getText() : null);
		if ( desc == null ) desc = "";
%>	
	<tr height="30" >
		<td width="150" align=left bgcolor="f4f4f4">
			&nbsp;<b>Description</b>
		</td>
		<td width="10"></td>
		<td width="*" align=left>
			<%=desc%>
		</td>
	</tr>
	<tr height="1">
		<td align="center" colspan="3" height="1" class="bgcolor_head_underline"></td>
	</tr>			
<%
	}
%>	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="10"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr height="1">
		<td align="center" height="1" class="bgcolor_head_upperline"></td>
	</tr>
	<tr height="5">
		<td align="center" height="5" class="bgcolor_head"></td>
	</tr>
	<tr height="1">
		<td align="center" height="1" class="bgcolor_head_underline"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr height="20" >
		<td width="150" align=left bgcolor="f4f4f4">
			&nbsp;<b>Object Definition</b>
		</td>
		<td width="10"></td>
		<td width="*" align=left>
<%
			
			String ruleDefInStr = pm.getResource(pdVer);
			
%><textarea name="definition" rows=17 cols=80><%=ruleDefInStr %></textarea>

		</td>		
	</tr>
	<tr height="1">
		<td align="center" colspan="3" height="1" class="bgcolor_head_underline"></td>
	</tr>
</table>		
			

	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="10"></td>
	</tr>
</table>

<input name="defId" value="<%=pd%>" type=hidden>
<input name="version" value="<%=pdr.getVersion()%>" type=hidden>
<input name="objectType" value="rule" type=hidden>
</form>


<form name="uploadFrm" method="post" enctype="multipart/form-data" action="xlsDTUpload.jsp">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr height="1">
		<td align="center" height="1" class="bgcolor_head_upperline"></td>
	</tr>
	<tr height="5">
		<td align="center" height="5" class="bgcolor_head"></td>
	</tr>
	<tr height="1">
		<td align="center" height="1" class="bgcolor_head_underline"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr height="1">
		<td align="center" colspan="3" height="1" class="bgcolor_head_underline"></td>
	</tr>			
	
	<tr height="30" >
		<td width="150" align=left bgcolor="f4f4f4">
			&nbsp;<b>Decision Table</b>
		</td>
		<td width="10"></td>
		<td width="*" align=left>
		<input name="fileName" type="file" size=20>&nbsp;
		<input name="XLS" type="button" value="XLS" onClick="uploadXLS()">
		</td>
	</tr>
	<tr height="1">
		<td align="center" colspan="3" height="1" class="bgcolor_head_underline"></td>
	</tr>
</table>

</form>

</body>
</html>

<%
}finally{
	if(pm!=null)
		try{pm.remove();}catch(Exception e){}
}
%>