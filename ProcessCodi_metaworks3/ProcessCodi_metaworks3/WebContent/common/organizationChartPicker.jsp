<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Organization Chart</title>
<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>
<%@include file="../common/styleHeader.jsp"%>

<style type="text/css">
	.topbutton {
		padding: 5px 0px;
		height: 25px;
	}
	
	.button {
		padding: 5px;
		font-family:굴림체;
		font-weight:bold;
		font-size:8pt;
	}

</style>

<%@ include file="../scripts/importCommonScripts.jsp"%>
<%@ include file="../common/importOrganizationTree.jsp"%>
<script type="text/javascript">
$(function(){
	var organizationTree = new OrganizationTree("divOrganizationTree", false);
	
	if(IS_MULTIPLE) {
		window.resizeTo(570, 590);

		$("#userSelecter").width(350).css(
			"float", "left"
		);
		
		$("#userMover").width(35).removeClass("hiddenIcon").css({
			"float": "left",
			"padding-top": "100px",
			"text-align": "center"
		});

		var jSelectedUsers = $("#selectedUsers").removeClass("hiddenIcon").css({
			"margin-left": "385px"
		});

		if ($.browser.msie && $.browser.version < 7) {
			jSelectedUsers.width(jSelectedUsers.width() - 4);
		}
		
		$("#thChkBoxAllUsers").append(
				$(document.createElement("input")).attr({
					"type": "checkbox",
					"id": "chkBoxAllUsers"
				}).click(function(){
					formatCheckBox('chkUser', this.checked);
				})
		);
		
		 //12.22 add
	    ELEMID = "<%=request.getParameter("elemid")%>";
		// 10.04 add
		addUserFromParent();
		
	} else {
		window.resizeTo(360, 590);
	} 
});
</script>
</head>
<body style="padding: 5px;" onload="document.forms[0].key.focus()">
<form id="mainForm">
	<div class="ui-widget ui-widget-header ui-corner-all" style="padding: 5px;"><%=GlobalContext.getMessageForWeb("Organization", loggedUserLocale)%></div>
	<div id="userSelecter">
		<div class="topbutton">
			<select name="column">
				<option value="empname"><%=GlobalContext.getMessageForWeb("User Name", loggedUserLocale)%></option>
				<option value="empcode"><%=GlobalContext.getMessageForWeb("User ID", loggedUserLocale)%></option>
				<option value="partname"><%=GlobalContext.getMessageForWeb("Department Name", loggedUserLocale)%></option>
				<option value="partcode"><%=GlobalContext.getMessageForWeb("Department Code", loggedUserLocale)%></option>
			</select>
			 &nbsp; 
			<input type="text" name="key" onkeypress="chkEnter(event);" />
			<input type="button" onmouseover="$(this).addClass('ui-state-hover');" onmouseout="$(this).removeClass('ui-state-hover')" onclick="searchUser();" value="<%=GlobalContext.getMessageForWeb("Search", loggedUserLocale)%>" class="ui-state-default ui-corner-all button"/>
		</div>
		<div id="divOrganizationTree" class="ui-widget-content ui-corner-all" style="overflow: auto; height: 160px;"></div>
		<div class="ui-widget-content ui-corner-all" style="height: 200px; padding: 5px; margin-top: 5px; overflow: auto;">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
				<col span="1" style="width: 30px" />
				<thead>
					<tr class="tabletitle ">
						<th id="thChkBoxAllUsers"></th>
						<th>CODE</th>
						<th>NAME</th>
						<th>PART</th>
						<th>POSI</th>
					</tr>
				</thead>
				<tbody id="tableUserList"></tbody>
			</table>
		</div>
	</div>
	<div id="userMover" class="hiddenIcon">
	   <img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/b_addR.gif" width="25" height="25" onclick="addUser();" style="cursor: pointer" />
	   <br />
	   <br />
	   <img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/b_addL.gif" width="25" height="25" onclick="deleteApproval();" style="cursor: pointer" />
	</div>
		
	<div id="selectedUsers" class="hiddenIcon">
		<div class="topbutton"><img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/I_info.gif" width="11" height="11" />
			Double click : Remove
		</div>
		<select class="ui-widget-content ui-corner-all" multiple="multiple" name="selectUserList" id="selectUserList" style="width: 100%; height: 380px;" ondblclick="deleteApproval();"></select>
	</div>
	<%
	String fncName = ((request.getParameter("fncName")==null) ? "onUserSelected" : request.getParameter("fncName"));
	%>
	<div style="padding: 10px; text-align: right;">
		<button onmouseover="$(this).addClass('ui-state-hover');" onmouseout="$(this).removeClass('ui-state-hover')" onclick="opener.<%=fncName%>(selectedOrganizationList, opener.inputName);window.close();" class="ui-state-default ui-corner-all button"><%=GlobalContext.getMessageForWeb("Ok", loggedUserLocale) %></button>
		&nbsp;
		<button onmouseover="$(this).addClass('ui-state-hover');" onmouseout="$(this).removeClass('ui-state-hover')" onclick="window.close();" class="ui-state-default ui-corner-all button"><%=GlobalContext.getMessageForWeb("Cancel", loggedUserLocale) %></button>
	</div>
</form>

</body>
</html>