<%@ page pageEncoding="UTF-8" language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*,org.springframework.web.util.HtmlUtils"%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache");
%>
<%@page import="org.uengine.security.AclManager"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../style/uengine.css" type="text/css" />
<link rel="stylesheet" href="../style/portlet.css" type="text/css"/>
<link rel="stylesheet" href="../style/portal.css" type="text/css"/>
<link rel="stylesheet" href="../style/groupware.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="../style/default.css" />
<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT %>/lib/ckeditor/ckeditor.js"></script>

<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>
<%@include file="../lib/jquery/importJquery.jsp"%>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />

<%
	ProcessManagerRemote pm = null;
	
	String pd = request.getParameter("objectDefinitionId");
	String pdVer = request.getParameter("processDefinitionVersionID");
	String folder = request.getParameter("folder");
	
	try {
		pm = processManagerFactory.getProcessManagerForReadOnly();
		ProcessDefinitionRemote[] arrPdr = pm.findAllVersions(pd);
		
		int maxVersion = -1;
		if(arrPdr != null){
			String versionID = null;
			int version = -1;
			for(int i=0; i<arrPdr.length; i++){
				versionID = arrPdr[i].getId();
				version = arrPdr[i].getVersion();
				if (arrPdr[i].isProduction()) {
					if( !UEngineUtil.isNotEmpty(pdVer) ) {
						pdVer = versionID;
					}
				}
			}
			
			if( !UEngineUtil.isNotEmpty(pdVer) ) {
				pdVer = versionID;
			}
			maxVersion = version + 1;
		}
		
		ProcessDefinitionRemote pdr = pm.getProcessDefinitionRemote(pdVer);
		
		//String title = pdr.getName().getText();
		String srAlias = pdr.getAlias();
		String belongingDefId = pdr.getBelongingDefinitionId();
		String formDefinitionValue = pm.getResource(pdVer);
	
		AclManager acl = AclManager.getInstance();
		HashMap permission = acl.getPermission(Integer.parseInt(pdr.getBelongingDefinitionId()), loggedUserId);
		if (loggedUserIsAdmin) {
	    	permission.put(AclManager.PERMISSION_MANAGEMENT, true);
	    	permission.put(AclManager.PERMISSION_EDIT, true);
	    	permission.put(AclManager.PERMISSION_INITIATE, true);
	    	permission.put(AclManager.PERMISSION_VIEW, true);
	    }
%>

<script>
	function makeProduction(){
		location="makeProduction.jsp?processDefinition=<%=pdVer.replace(' ', '+')%>";
	}
	
	function saveForm(){
	
		document.actionForm.submit();
	}
	
	function updateForm(){
		document.actionForm.version.value = '<%=pdr.getVersion()%>';
		document.actionForm.submit();
	}
	
	function refresh(){
		var pdv = document.all.processDefinitionVersionID.value;
		location="?objectDefinitionId=<%=pd%>&folder=<%=folder%>&processDefinitionVersionID=" + pdv;
	}
</script>
<title>
	<%=GlobalContext.getMessageForWeb("Form Definition", loggedUserLocale) %> -
	<%=pdr.getName().getText()%> (
	<%=GlobalContext.getMessageForWeb("Version", loggedUserLocale) %> : <%=pdr.getVersion()%> / 
	<%=GlobalContext.getMessageForWeb("Modified Date", loggedUserLocale) %> : <%=pdr.getStrModifiedDate() %>
	)
</title>
</head>

<body>
<% if (permission.containsKey(AclManager.PERMISSION_EDIT)) { %>
<form name="actionForm" action="saveFormDefinition.jsp" method="post">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td valign="middle">
			<img src="../images/Common/I_info.gif" align="middle" border="0" style="margin-top:-2px;"> <strong><%=GlobalContext.getLocalizedMessageForWeb("edit_page", loggedUserLocale, "Edit Page") %></strong>
		</td>
        <td>
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<%
                    if (pdVer != null) {
                %>
                    
                    <tr>
                        <td>	
                            
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="right" valign="top">
                                    <table cellSpacing="0" cellpadding="0">
                                        <tBody>
                                            <tr>
                                                <td class="gBtn">
                                                	<% if (
                                                    		permission.containsKey(AclManager.PERMISSION_MANAGEMENT)
                                                    		|| permission.containsKey(AclManager.PERMISSION_EDIT)
                                                    	) {
                                                    %>
                                                    <a href="javascript: saveForm();"><span>
                                                        <%=GlobalContext.getLocalizedMessageForWeb("save_new_version", loggedUserLocale, "Save New Version") %>
                                                    </span></a>
                                                    <% } %>
                                                    <% if (permission.containsKey(AclManager.PERMISSION_MANAGEMENT)	) { %>
                                                    <a href="javascript: updateForm();" key="button.update"><span>
                                                        <%=GlobalContext.getLocalizedMessageForWeb("update_for_this_version", loggedUserLocale, "Update for This Version") %>
                                                    </span></a>
                                                    <!-- 
                                                    <a href="javascript: removeDefinition('<%=belongingDefId%>');"><span>
                                                        <%=GlobalContext.getLocalizedMessageForWeb("delete", loggedUserLocale, "Delete") %>
                                                    </span></a>
                                                     -->
													<%
                                                    }
                                                            if(pdr.isProduction()) {
                                                    %>
                                                    <span style="line-height:23px; color:#F00">
                                                    [<%=GlobalContext.getLocalizedMessageForWeb("this_version_is_procuction", loggedUserLocale, "This version is production") %>]
                                                    </span>
            										<% 	} else if (loggedUserIsAdmin) { %>	
                                                    <a href="javascript: makeProduction();"><span>
                                                        <%=GlobalContext.getLocalizedMessageForWeb("set_as_production", loggedUserLocale, "Set As Production") %>
                                                    </span></a>
            										<%	} %>
                                                </td>
                                            </tr>
                                        </tBody>
                                    </table>
                                    
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>			
                <%
                    }
                %>
            </table>	
        </td>
        
	</tr>
    <tr>
    	<td height="10"></td>
    </tr>
	
</table>


		
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="5"></td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr >
		<td align="center" colspan="3" class="formheadline"></td>
	</tr>			

	<tr height="30" >
		<td width="150" class="formtitle">
			&nbsp;<b><%=GlobalContext.getMessageForWeb("Definition", loggedUserLocale) %></b>
		</td>
		<td width="10"></td>
		<td width="*" align="left">
			<%=pdr.getName().getText()%> (
				<%=GlobalContext.getMessageForWeb("ID", loggedUserLocale) %>  : <%=pdr.getBelongingDefinitionId()%> ,
				<%=GlobalContext.getMessageForWeb("Alias", loggedUserLocale) %> : <%=srAlias %> 
			)
		</td>
	</tr>
	<tr >
		<td align="center" colspan="3" class="formline" height="1"></td>
	</tr>
<%
	if(arrPdr != null){
%>	<tr height="30" >
		<td width="150" class="formtitle">
			&nbsp;<strong><%=GlobalContext.getMessageForWeb("Version", loggedUserLocale) %></strong>
		</td>
		<td width="10"></td>
		<td width="*" align="left">
			<select name="processDefinitionVersionID" id="processDefinitionVersionID" onChange="refresh();">	
<%	
			String versionID = null;
			String version = "-1";
			String strModifiedDate = "";
			String sStyle = "";
			
			for(int i=0; i<arrPdr.length; i++){
				strModifiedDate = arrPdr[i].getStrModifiedDate();
				versionID = arrPdr[i].getId();
				version = "Ver : " + arrPdr[i].getVersion();
				
				if (arrPdr[i].isProduction()) {
					version =  version + "*";
					sStyle = " style=\"color: #FF0000;\" ";
				} else {
					sStyle = "";
				}
%>
				<option value="<%=versionID%>" <%=sStyle %> <%=(versionID.equals(pdVer)) ? "selected=\"selected\" " : ""%>><%=version%></option>
<%		} %>
			</select>
				( <%=GlobalContext.getMessageForWeb("ID", loggedUserLocale) %>  : <%=pdVer%> ,
				<%=GlobalContext.getMessageForWeb("Modified Date", loggedUserLocale) %> : <%=pdr.getStrModifiedDate() %> )
		</td>
	</tr>
	<tr height="1">
		<td align="center" colspan="3" class="formline" height="1"></td>
	</tr>
<%
	}

	if (pdVer!=null) { 
		String desc = (pdr.getDescription()!=null ? pdr.getDescription().getText() : null);
		if ( desc == null ) desc = "";
%>	
	<tr height="30" >
		<td width="150" class="formtitle">
			&nbsp;<b><%=GlobalContext.getLocalizedMessageForWeb("description", loggedUserLocale, "Description") %></b>
		</td>
		<td width="10"></td>
		<td width="*" align="left">
			<%=desc%>
		</td>
	</tr>
	<tr >
		<td align="center" colspan="3" class="formheadline"></td>
	</tr>			
<%
	}
%>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="2"></td>
	</tr>
</table>



<!-- 폼 -->
<textarea id="CKeditor1" name="CKeditor1" ><%=HtmlUtils.htmlEscape(formDefinitionValue)%></textarea>
<script type="text/javascript">
//<![CDATA[
	CKEDITOR.replace('CKeditor1',
	{
		skin : '<%=GlobalContext.getPropertyString("ckeditor.skin", "kama")%>', 
		width : ($("iframe[name='innerworkarea']").attr("width") - 25) + "px", 
		height : ($(document).height() - 320) + "px"
	});
	CKEDITOR.config.protectedSource.push( /<\%[\s\S]*?\%>/g );
//]]>
</script>

<span style="visibility:hidden;"><textarea name="ValueContent"><%=formDefinitionValue%></textarea></span>

<input type="hidden" name="defId" 					value="<%=pd%>" />
<input type="hidden" name="definitionName" 		value="<%=pdr.getName().getText()%>" />
<input type="hidden" name="version" 					value="<%=maxVersion%>" />
<input type="hidden" name="objectType" 				value="form" />
</form>
<% } else { %>
 You don't have permission view.
<% } %>
</body>
</html>
<%
	} catch(Exception e) {
		e.printStackTrace();
		throw e;
	} finally {
		if (pm != null) pm.remove();
	}
%>