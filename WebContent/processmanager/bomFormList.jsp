<%@ page contentType="text/xml; charset=UTF-8" language="java" import="java.sql.*,javax.sql.*,org.uengine.util.dao.DefaultConnectionFactory"  
%><?xml version="1.0" encoding="UTF-8"?>
<%
response.setContentType("text/xml; charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%><%@include file="../common/headerMethods.jsp"%>

<%-- DB에서 값 읽어서 출력 하기 --%>
<imfg_outputs>
	<imfg_output value="악악" name="formId"/>
</imfg_outputs>
