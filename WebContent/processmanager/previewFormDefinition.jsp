<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache");
%>

<%@page import="org.uengine.util.*" %>
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />

<%@include file="../common/header.jsp"%>
<%@include file="../wih/wihDefaultTemplate/definition.jsp" %>

<LINK rel="stylesheet" href="../style/formdefault.css" type="text/css"/>
<script type="text/javascript" src="../scripts/calendar.js"></script>
<script type="text/javascript" src="../scripts/calendar-setup.js"></script>
<script type="text/javascript" src="../scripts/calendar-en.js"></script>

<style TYPE="TEXT/CSS">
BODY {background:url(images/empty.gif) #ffffff }
</style>

<%
	String defVerId = request.getParameter("defVerId");
	String html = "";
	try{	
		pm = processManagerFactory.getProcessManagerForReadOnly();
		html = pm.getResource(defVerId);
		System.out.println(html);
	}finally{
		try{pm.cancelChanges();}catch(Exception ex){}
		try{pm.remove();}catch(Exception ex){}
	}
%>
<br><br>
<center>
<div>
<%=html%>
</div>
</center>