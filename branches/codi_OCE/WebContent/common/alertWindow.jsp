<%@include file="header.jsp"%>
<%@include file="getLoggedUser.jsp"%>
<html>
<head>
<style TYPE="TEXT/CSS">
BODY {background:url(../../processmanager/images/empty.gif) #ffffff }
</style>
</head>
<body>
<table width=100% height=100%>
	<tr>
		<td>
		<div class="portlet-msg-success">
			<%=GlobalContext.getLocalizedMessageForWeb("disable_fuction", loggedUserLocale, "The Enterprise Edition provides this function for you") %>
		</div>
		</td>
	<tr>
</table>	
</body>
</html>
