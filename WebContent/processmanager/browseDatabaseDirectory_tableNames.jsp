<%@page contentType="text/xml; charset=UTF-8" import="java.util.*,org.uengine.kernel.*,org.uengine.kernel.descriptor.*,org.uengine.processmanager.*,org.uengine.util.dao.ConnectionFactory"%>
<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
response.setContentType("text/xml; charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	
String serConnectionFactory = request.getParameter("connectionFactory");
String tableName = request.getParameter("tableName");

ConnectionFactory cf =null;

if(serConnectionFactory!=null)
	cf = (ConnectionFactory)GlobalContext.deserialize(serConnectionFactory);
else
	cf = null;

List parameterList = org.uengine.kernel.descriptor.DatabaseMappingActivityDescriptor.getTableNames(cf);
out.println(GlobalContext.serialize(parameterList, List.class));
%>
