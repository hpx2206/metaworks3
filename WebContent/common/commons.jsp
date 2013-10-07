<%@page import="org.uengine.ui.taglibs.input.InputConstants"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map,java.util.Enumeration"%>
<%@page import="java.util.Iterator,java.util.ArrayList"%>
<%@page import="java.util.List,org.uengine.kernel.*"%>
<%@page import="org.uengine.processmanager.*"%>
<%@ taglib uri="http://www.uengine.org/taglibs/input" prefix="input" %>

<%
    boolean isCompleted =false;
    ProcessManagerRemote manager=null;
    ProcessInstance instance=null;
    RoleMapping loggedRoleMapping=null;
    FormActivity formActivity=null;
    try{
		formActivity = (FormActivity)request.getAttribute("formActivity");
		loggedRoleMapping = (RoleMapping)request.getAttribute("loggedRoleMapping");
		instance = (ProcessInstance)request.getAttribute("instance");
		manager = (ProcessManagerRemote)request.getAttribute("pm");
        isCompleted  ="Completed".equals( formActivity.getStatus(instance));
	}catch(Exception e){}
%>
