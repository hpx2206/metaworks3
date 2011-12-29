<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<head>
<%@include file="../common/topFrame/indexHeadFrame.jsp" %>
</head>
<body>
<div id="bc" style="width:100%; height:100%;" dojoType="dijit.layout.BorderContainer">
	<div dojoType="dojox.layout.ExpandoPane" 
					splitter="true" 
					duration="125" 
					region="left" 
					title="<%=GlobalContext.getMessageForWeb("Process", loggedUserLocale)%>" 
					id="leftPane" 
					maxWidth="275" 
					style="width: 250px; background: #fafafa;">
		<%@include file="processDefinitionList.jsp" %>
	</div>
	<div dojoType="dijit.layout.ContentPane" region="center" id="centerPane" style="overflow: hidden;">
		<%@include file="../common/topFrame/titleBar.jsp" %>
	     <iframe onresize="resizeFrame(this);" onload="changeTitle(this);" style="width: 100%; height: 100%; padding-bottom: 30px;" src="<%=GlobalContext.WEB_CONTEXT_ROOT %>/processmanager/processInstanceList.jsp" 
	     name="innerworkarea" frameborder="0"></iframe>
	</div>
</div>

</body>
</html>