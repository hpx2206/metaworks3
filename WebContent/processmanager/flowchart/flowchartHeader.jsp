<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="org.uengine.kernel.GlobalContext"%>
<%@page import="org.uengine.kernel.viewer.ViewerOptions"%>
<%@page import="org.uengine.ui.list.datamodel.DataMap"%>
<%@page import="org.uengine.ui.list.util.HttpUtils"%>

<%@include file="../../common/styleHeader.jsp"%>
<%@include file="../../lib/jquery/importJquery.jsp" %>

<script type="text/javascript">
<!--
var WEB_CONTEXT_ROOT = "<%=GlobalContext.WEB_CONTEXT_ROOT%>";
//-->
</script>
<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/lib/raphael/raphael-min.js"></script>
<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/scripts/ajax/ajaxCommon.js"></script>
<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/scripts/crossBrowser/elementControl.js"></script>

<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/scripts/flowchart/flowchartDefinition.js"></script>
<!-- 
<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/scripts/flowchart/flowchartNoAnyUtil.min.js"></script>
-->
<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/scripts/flowchart/flowchartUtil.js"></script>
<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/scripts/loopDraw.js"></script>


<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/scripts/popupTooltip.js"></script>

<LINK type="text/css" rel="stylesheet" href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/style/popupTooltip.css">
<LINK type="text/css" rel="stylesheet" href="<%=GlobalContext.WEB_CONTEXT_ROOT%>/style/flowchart.css">
<%
ViewerOptions options = new ViewerOptions();

String instanceId 				= reqMap.getString("instanceId", "");
String processDefinition 		= reqMap.getString("processDefinition", "");
String definitionVersionId 		= reqMap.getString("definitionVersionId", "");
String viewFrame 				= reqMap.getString("viewFrame", "");
String lastInstance 			= reqMap.getString("lastInstance", "");
String initiate 				= reqMap.getString("initiate", "");
String parentPdver 				= reqMap.getString("parentPdver", "");
String viewType 				= request.getParameter("viewType");
String viewOption 				= request.getParameter("viewOption");
%>

<script type="text/javascript">
	function initateProcess(defverid) {
		var url = WEB_CONTEXT_ROOT + "/processparticipant/initiateForm.jsp?processDefinition=" + defverid;
		window.top.location.href = url;
	}
	
	function init() {
		getProcessFlowchart(
				"<%=instanceId %>",
				"<%=processDefinition %>",
				"<%=definitionVersionId %>",
				"<%=parentPdver %>",
				"<%=viewType %>",
				"<%=viewOption %>",
				"<%=lastInstance %>",
				"<%=initiate %>"
		);
	}
</script>


