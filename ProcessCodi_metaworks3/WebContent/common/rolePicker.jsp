<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>
<%
	String type = decode(request.getParameter("type"));
	String objType = decode(request.getParameter("objType"));
	String empCode = decode(request.getParameter("empCode"));
	String empName = decode(request.getParameter("empName"));
	String groupCode = decode(request.getParameter("groupCode"));
	String groupName = decode(request.getParameter("groupName"));
	String roleCode = decode(request.getParameter("roleCode"));
	String roleName = decode(request.getParameter("roleName"));
%>
<style type="text/css">
@import "../dojo/dojo/resources/dojo.css";
@import "../dojo/dijit/tests/css/dijitTests.css";
@import "../dojo/dojox/grid/_grid/soriaGrid.css";
@import "../dojo/dijit/themes/soria/soria.css";

/* NOTE: for a full screen layout, must set body size equal to the viewport. */
body {
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
	overflow: hidden;
}
</style>
<title>Organization List</title>
<script type="text/javascript" src="../dojo/dojo/dojo.js"
	djConfig="parseOnLoad: true, isDebug: false, defaultTestTheme: 'soria'"></script>
<script type="text/javascript" src="../dojo/dijit/tests/_testCommon.js"></script>
<script type="text/javascript">
		dojo.require("dojo.parser");
		dojo.require("dijit.Declaration");
		dojo.require("dijit.layout.LayoutContainer");
		dojo.require("dijit.layout.ContentPane");
		dojo.require("dijit.form.Button");
		dojo.require("dojo.data.ItemFileReadStore");
		dojo.require("dojox.grid.Grid");
		dojo.require("dojox.grid._data.model");

		var roleList = {
				cells: [
						[
							{name: 'Role Code', width: "30%", field: "code"},
							{name: 'Role Name', width: "70%", field: "name"}
						],
						[]
				]
		};
		var roleListLayout = [ roleList ];
		
     	function doubleClick(evt){
	     	var row = evt.rowIndex;
	     	var item = model.getRow(row);
	     	var roleCode = item.code;
	     	var roleName = item.name;

     		var oSelect = document.getElementById("selectedRoleList");
     		var oOption = document.createElement("option");

     		oOption.value = roleCode;
     		oOption.text = roleName;
     		oSelect.add(oOption);
     	}
		
		function deleteApproval(oSelect) {
			var i = oSelect.selectedIndex;
			if (oSelect.selectedIndex != -1) {
				oSelect.options[i] = null;
			}
		}

		function setRoleData() {
			var oSelect = document.getElementById("selectedRoleList");
			var values = "";
			var labels = "";
			
			for (var i = 0; i < oSelect.options.length; i++) {
				var oOption = oSelect.options[i];
				values += oOption.value;
				labels += oOption.text;
				
				if (i < oSelect.options.length - 1) {
					values += " ;";
					labels += " ;";
				}
			}
			
			var data = new Array();
			data[0] = values;
			data[1] = labels;
			window.opener.setRoleInput(data);

			window.close();
		}
     </script>
<link href="../style/default.css" rel="stylesheet" type="text/css">
</head>
<body class="soria">
<form name="formRole">
<table width="100%" height="100%" border="0">
	<tr height="*">
		<td width="400">
			<div dojoType="dojo.data.ItemFileReadStore" jsId="organizationRoleStore"
				url="../organizationmanager/organizationRoleListJson.jsp"></div>
			<div dojoType="dojox.grid.data.DojoData" jsId="model" rowsPerPage="20"
				store="organizationRoleStore" query="{ objType: 'role' }"></div>
			
			<div dojoType="dijit.layout.LayoutContainer"
				style="width: 100%; height: 100%; padding: 0; margin: 0; border: 0;">
			<div id="grid" dojoType="dojox.Grid" layoutAlign="client" model="model"
				onRowDblClick="doubleClick" defaultHeight="50" noscroll="1" structure="roleListLayout"></div>
			</div>
		</td>
		<td width="*"><div id="divUserList"></div></td>
	</tr>
	<tr height="40">
	   <td colspan="2">
	       <select id="selectedRoleList" style="width: 100%;" size="5" ondblclick="deleteApproval(this);"></select>
	   </td>
	</tr>
	<tr height="30">
	   <td colspan="2" align="right">
	       <table>
	           <tr>
	               <td class="gBtn">
			           <a onclick="setRoleData();"> <span><%=GlobalContext.getMessageForWeb("Register", loggedUserLocale) %></span></a>
			           <a onclick="window.close();"> <span><%=GlobalContext.getMessageForWeb("Cancel", loggedUserLocale) %></span></a>
			       </td>
			   </tr>
		  </table>
       </td>
	</tr>
</table>
</form>
</body>

</html>