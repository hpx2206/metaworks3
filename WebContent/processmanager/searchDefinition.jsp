<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="org.uengine.persistence.dao.DAOFactory"%>
<%@page import="org.uengine.util.dao.DefaultConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>

<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>

<%
	String strSearchWord = UEngineUtil.searchStringFilter(reqMap.getString("inputSearchWord", ""));
	String strSearchAlias = UEngineUtil.searchStringFilter(reqMap.getString("inputSearchAlias", ""));
	String strObjType = UEngineUtil.searchStringFilter(request.getParameter("inputObjType"));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=GlobalContext.getMessageForWeb("Search Definition", loggedUserLocale) %></title>
        
		<%@include file="../common/styleHeader.jsp"%>
        <style type="text/css">
        tbody {
        	font-size: 10pt;
        }
        tfoot {
        	font-size: 12pt;
        	font-weight: bold;
        }
		body{width:98%; margin:15px;}
        </style>
		<script type="text/javascript">
		function checkItem() {
			var options = document.getElementById("inputObjType").options;
			for (var i = 0; i < options.length; i++) {
				option = options[i];
				if (option.value == "<%=strObjType%>") {
					option.selected = true;
				}
			}
		}

		var strStatus = 'toolbar=0, location=0, status=0, menubar=0, scrollbars=1, resizable=1, width=950, height=700, top=50, left=50';
		</script>
        <link href="../style/default.css" rel="stylesheet" type="text/css">	
    </head>
    <body onLoad="checkItem();">
    <div style="width:100%;">
    <form action="searchDefinition.jsp" method="post">
	    <table width="100%">
	    	<tr><td width="100%">
	    		<strong>
	    			<%=GlobalContext.getMessageForWeb("DEFINITION", loggedUserLocale) %>
	    			<%=GlobalContext.getMessageForWeb("TYPE", loggedUserLocale) %> : 
	    		</strong>
	    		<select name="inputObjType" id="inputObjType">
	    			<option value="all"><%=GlobalContext.getMessageForWeb("ALL", loggedUserLocale) %></option>
	    			<option value="process"><%=GlobalContext.getMessageForWeb("PROCESS", loggedUserLocale) %></option>
	    			<option value="form"><%=GlobalContext.getMessageForWeb("FORM", loggedUserLocale) %></option>
	    		</select>
	    		<strong><%=GlobalContext.getMessageForWeb("NAME", loggedUserLocale) %> : </strong>
	    		<input name="inputSearchWord" type="text" style="width:200px;" value="<%=strSearchWord %>" />
	    		<strong><%=GlobalContext.getMessageForWeb("ALIAS", loggedUserLocale) %> : </strong>
	    		<input name="inputSearchAlias" type="text" style="width:200px;" value="<%=strSearchAlias %>" />
	    		<input type="submit" value="<%=GlobalContext.getMessageForWeb("SEARCH", loggedUserLocale) %>" />
	    	</td></tr>
	    </table>
    </form>
<%
	if (UEngineUtil.isNotEmpty(strObjType)) {
%>
	<br>
   <table width="100%" bgcolor="#CCCCCC" cellpadding="3" cellspacing="1">
   <col span="1" width="50px" align="center" />
   <col span="1" width="70px" align="center" />
   <col span="1" width="170px">
   <col span="1" width="230px">
   <col span="1" width="*">
   		<thead>
    		<tr class="tabletitle">
    			<th><%=GlobalContext.getMessageForWeb("ID", loggedUserLocale) %></th>
    			<th><%=GlobalContext.getMessageForWeb("VIEW", loggedUserLocale) %></th>
    			<th><%=GlobalContext.getMessageForWeb("ALIAS", loggedUserLocale) %></th>
    			<th><%=GlobalContext.getMessageForWeb("NAME", loggedUserLocale) %></th>
    			<th><%=GlobalContext.getMessageForWeb("DESCRIPTION", loggedUserLocale) %></th>
    		</tr>
    	</thead>
    	<tbody>
<%
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuffer strSqlWhere = new StringBuffer();
			
			String dBMSProductName = null;
			try
			{
				dBMSProductName = DAOFactory.getInstance().getDBMSProductName();
			} catch (Exception e) {}
				
			if ("MySQL".equals(dBMSProductName))
			{
				strSqlWhere.append(" AND name LIKE CONCAT('%', ?, '%') AND alias LIKE CONCAT('%', ?, '%') ");
			}
			else
			{
				strSqlWhere.append(" AND name LIKE '%'||?||'%' AND alias LIKE '%'||?||'%' ");
			}
			
			if (UEngineUtil.isNotEmpty(strObjType) && !"all".equals(strObjType)) {
				strSqlWhere.append(" AND objtype = '" + strObjType + "' ");
			}
			
			StringBuffer SQL = new StringBuffer(
					"SELECT * FROM bpm_procdef "
					+ " WHERE isfolder = 0 "
					+ " AND isdeleted = 0 "
					+ strSqlWhere
					+ " ORDER BY defid ASC"
			);
			
			conn = DefaultConnectionFactory.create().getConnection();
			pstmt = conn.prepareStatement(SQL.toString());
			
			pstmt.setString(1, strSearchWord);
			pstmt.setString(2, strSearchAlias);
			
			rs = pstmt.executeQuery();
			
			int iRowCount = 0;
			while (rs.next()) {
				iRowCount++;
				String strUrl = null;
				String strType = GlobalContext.getMessageForWeb(rs.getString("OBJTYPE").toLowerCase(), loggedUserLocale);
				
				if ( "form".equalsIgnoreCase(rs.getString("OBJTYPE")) ) {
					strUrl = "viewFormDefinition.jsp?objectDefinitionId=" + rs.getInt("DEFID");
					strType = GlobalContext.getMessageForWeb("Form", loggedUserLocale);
				} else {
					strUrl = "viewProcessFlowChart.jsp?processDefinition=" + rs.getInt("DEFID")
							+ "&processDefinitionVersionID=" + rs.getInt("PRODVERID");
					strType = GlobalContext.getMessageForWeb("Process", loggedUserLocale);
				}
%>
			<tr bgcolor="#FFFFFF" onMouseOver="this.style.backgroundColor = '#EEEEEE';" height="25" onMouseOut="this.style.backgroundColor = '';">
				<td><%= rs.getInt("DEFID") %></td>
				<td>
					<input type="button" value="<%= strType %>" style="width: 60px;" title="View <%= strType %>" 
					onclick="window.open('<%=strUrl %>', '_new', strStatus, false)" />
				</td>
    			<td>[<%= rs.getString("alias") %>]@<%= rs.getInt("PRODVERID") %></td>
    			<td><%= rs.getString("NAME") %></td>
    			<td><%= UEngineUtil.isNotEmpty(rs.getString("DESCRIPTION")) ? rs.getString("DESCRIPTION") : "&nbsp;" %></td>
    		</tr>
<%
			}
%>
			</tbody>
			<tfoot>
			<tr class="tabletitle"><td colspan="5" align="center" height="20px">
<% 		
			if (iRowCount < 1) {
				out.println("DataBase Have Not");
			} else {
				out.println("Data Count : " + iRowCount);
			} 
%>
			</td></tr>
			</tfoot>
<%
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) { try {pstmt.close();} catch(SQLException ex) {}}
			if (conn != null) { try {conn.close();} catch(SQLException ex) {}}
		}
	}

%>
    	</table>
    </div>
    <br /><br />
    </body>
</html>