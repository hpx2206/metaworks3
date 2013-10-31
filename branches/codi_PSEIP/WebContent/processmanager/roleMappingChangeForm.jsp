
<%@include file="../common/styleHeader.jsp"%>

<html>
<head>
<title></title>
<script type="text/javascript">
	var WEB_ROOT = "<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT%>";
	
	function changeRoleMapping() {
		var selectedUserList = document.mainForm.selectUserList;
		var returnStr = "";

		var endpoint = document.getElementsByName("endpoint")[0];
		var roleName = document.getElementsByName("roleName")[0];
		
		if (selectedUserList != undefined && selectedUserList.options.length != 0) {
			for (i=0; i<selectedUserList.options.length; i++) {
				var userInfo = selectedUserList.options[i].value;

				var userInfoArray = userInfo.split(';');		
				var userId = userInfoArray[0].split('=');
				
				returnStr += userId[1];
				if (i != selectedUserList.options.length -1)
					returnStr += ";";
			}
			
			endpoint.value = returnStr;
		} else {
			if (selectedOrganizationList.length > 0) {
                endpoint.value = selectedOrganizationList[0].id;
            }
		}
		
		if (endpoint.value == "") {
			alert("Select User");
		} else {
			if (roleName.value.match(/TRCTAG\[[0-9]{1,}\]:.*/gi) != null) {
				var orgRoleName = roleName.value.replace(/TRCTAG\[[0-9]{1,}\]:/gi, "");
				if (confirm("Change " + orgRoleName + " role, too?")) {
					document.getElementsByName("orgRoleName")[0].value = orgRoleName;
				}
			} 
			document.roleMappingChangeForm.submit();
		}
	}

	function init() {
		var roleName = document.getElementsByName("roleName")[0];
		if (roleName.value == "Initiator") {
			alert("Can't change initiator");
			window.close();
		}
	}
	
	function onOk(selectedOrganizationList, opnerInputName) {
    }
</script>
</head>

<body onload="init()">
<form name="mainForm">
<%@include file="../common/organizationChartHeader.jsp"%>
</form>

<form name="roleMappingChangeForm" action="roleMappingChange.jsp" method="POST">
	<input type="hidden" name="endpoint" value="">
	<input type="hidden" value="<%=decode(request.getParameter("instanceId"))%>" name="instanceId">
	<input type="hidden" value="<%=decode(request.getParameter("roleName"))%>" name="roleName">
	<input type="hidden" value="" name="orgRoleName">
	<div align="center">
	<br /><br />
	<table>
	    <tr>
			<td class="gBtn">
	            <a onclick="changeRoleMapping();">
	            	<span><%=GlobalContext.getMessageForWeb("Ok", loggedUserLocale) %></span>
	            </a>
	            <a onclick="window.close();" >
	            	<span><%=GlobalContext.getMessageForWeb("Cancel", loggedUserLocale) %></span>
	            </a>
			</td>
	    </tr>
	</table>
	</div>
</form>

<br><br>



</body>
</html>