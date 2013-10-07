<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="java.util.*"%>

<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="javax.rmi.PortableRemoteObject"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.NamingException"%>

<%@ page import="org.metaworks.*"%>
<%@ page import="org.metaworks.web.*"%>
<%@ page import="org.metaworks.inputter.*"%>
<%@ page import="org.uengine.ui.list.util.HttpUtils"%>
<%@ page import="org.uengine.ui.list.datamodel.DataMap"%>
<%@ page import="org.uengine.util.UEngineUtil"%>
<%@ page import="org.uengine.processmanager.*"%>
<%@ page import="org.uengine.kernel.*"%>
<%@ page import="org.uengine.webservices.worklist.*"%>
<%@ page import="org.uengine.webservice.*"%>
<%@ page import="org.uengine.security.AclManager" %>
<%@ page import="org.uengine.contexts.*"%>

<%@ include file="headerMethods.jsp"%>


<% 
	request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache");

	DataMap reqMap = HttpUtils.getDataMap(request, false);
%>
