<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	boolean isMultiple = org.uengine.util.UEngineUtil.isTrue(request.getParameter("multiple"));
	boolean isApproval = org.uengine.util.UEngineUtil.isTrue(request.getParameter("isApproval"));
%>
<script type="text/javascript">
	var IS_MULTIPLE = <%=isMultiple%>;
	var IS_APPROVAL = <%=isApproval%>;
	
	var loggedUserGlobalCom = "<%=loggedUserGlobalCom%>";
</script>
<script type="text/javascript" src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT%>/lib/jsTree/_lib/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT%>/lib/jsTree/_lib/jquery.hotkeys.js"></script>
<script type="text/javascript" src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT%>/lib/jsTree/jquery.jstree.js"></script>
<script type="text/javascript" src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT%>/scripts/ajax/userList.js"></script>
<script type="text/javascript" src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT%>/scripts/organization/util.js"></script>
<script type="text/javascript" src="<%=org.uengine.kernel.GlobalContext.WEB_CONTEXT_ROOT%>/scripts/organization/treeView.js"></script>