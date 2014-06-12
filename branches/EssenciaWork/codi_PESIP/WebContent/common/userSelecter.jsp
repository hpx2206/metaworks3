<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Organization Chart</title>
	
	<%@include file="../common/styleHeader.jsp"%>
	<style type="text/css">
		html,body {
			margin: 0px;
			padding: 0px;
		}
		
		.button {
			padding: 5px;
		}
	
		.topbutton {
			padding: 5px;
			height: 25px;
		}
	</style>
	
	<%@ include file="../scripts/importCommonScripts.jsp"%>
	<%@ include file="../common/importOrganizationTree.jsp"%>
	
	<script type="text/javascript">
		$(function(){
			var organizationTree = new OrganizationTree("divOrganizationTree", false);
			if (IS_MULTIPLE) {
				$("#thChkBoxAllUsers").append(
						$(document.createElement("input")).attr({
							"type": "checkbox",
							"id": "chkBoxAllUsers"
						}).click(function(){
							formatCheckBox('chkUser', this.checked);
						})
				);
			}
		});
	</script>
</head>
<body>
	<form name="mainForm">
	<div class="ui-widget-content ui-corner-all" style="padding: 5px;">
	<div class="ui-widget ui-state-default ui-corner-all" style="padding: 5px;">Organization Chart</div>
	<div>
		<div class="topbutton">
			<select name="column">
				<option value="empname"><%=GlobalContext.getMessageForWeb("User Name", loggedUserLocale)%></option>
				<option value="empcode"><%=GlobalContext.getMessageForWeb("User ID", loggedUserLocale)%></option>
				<option value="partname"><%=GlobalContext.getMessageForWeb("Department Name", loggedUserLocale)%></option>
				<option value="partcode"><%=GlobalContext.getMessageForWeb("Department Code", loggedUserLocale)%></option>
			</select>
			 &nbsp; 
			<input type="text" name="key" onkeypress="chkEnter(event);" />
			<input type="button" onclick="searchUser();" value="<%=GlobalContext.getMessageForWeb("Search", loggedUserLocale)%>" />
		</div>
		<div id="divOrganizationTree" class="ui-widget-content ui-corner-all" style="overflow: auto; height: 200px;"></div>
		<div class="ui-widget-content ui-corner-all" style="height: 250px; padding: 5px; margin-top: 5px; overflow: auto;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<col span="1" style="width: 30px" />
				<thead>
					<tr class="tabletitle">
						<th class="ui-widget ui-state-default" id="thChkBoxAllUsers"></th>
						<th class="ui-widget ui-state-default">CODE</th>
						<th class="ui-widget ui-state-default">NAME</th>
						<th class="ui-widget ui-state-default">PART</th>
						<th class="ui-widget ui-state-default">POSI</th>
					</tr>
				</thead>
				<tbody id="tableUserList"></tbody>
			</table>
		</div>
	</div>
	
	</div>
	</form>
</body>
</html>
